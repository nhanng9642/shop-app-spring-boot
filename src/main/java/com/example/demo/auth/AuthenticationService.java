package com.example.demo.auth;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ExpiredTokenException;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtService;
import com.example.demo.token.Token;
import com.example.demo.token.TokenRepository;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserDTO;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.Constant;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.json.JsonFactory;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmailService emailService;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String CLIENT_ID;

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Value("${application.security.jwt.expiration}")
    private int timeExpiration;

    public ApiResponse login(@Valid LoginRequest request) {
        String username = request.getUsername();
        User user = userRepository.findUserByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or email not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Password is incorrect");
        }

        String accessToken = jwtService.generateJwtToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(user, refreshToken);
        var data =  AuthenticationResponse
                .builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .user(new UserDTO(user))
                .timeExpiration(timeExpiration)
                .build();

        return ApiResponse
                .builder()
                .success(true)
                .data(data)
                .message("Login successfully")
                .build();
    }

    public ApiResponse register(@Valid RegisterRequest request){
        User user = User
                .builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(Role.ROLE_USER)
                .build();

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new BadRequestException("Username is already taken");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("Email is already taken");
        }

        String accessToken = jwtService.generateJwtToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        userRepository.save(user);
        saveUserToken(user, refreshToken);

        var token = AuthenticationResponse
                .builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .timeExpiration(timeExpiration)
                .build();

        return ApiResponse
                .builder()
                .success(true)
                .data(token)
                .message("Register successfully")
                .build();
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        int beginTokenIndex = 7; //length of "Bearer "
        final String refreshToken = header.substring(beginTokenIndex);
        final String username = jwtService.extractUsername(refreshToken);

        if (username != null) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            Token token = tokenRepository.findByToken(refreshToken)
                    .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Token is invalid"));

            if (token.isRevoked())
                throw new BadRequestException("Token is revoked");

            if (jwtService.isTokenExpired(refreshToken))
                throw new BadRequestException("Token is expired");

            String accessToken = jwtService.generateJwtToken(user);
            return AuthenticationResponse
                    .builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .timeExpiration(timeExpiration)
                    .build();

        }
        throw new BadRequestException("Token is not valid");
    }

    private void saveUserToken(User user, String jwt) {
        Token token = Token
                .builder()
                .token(jwt)
                .user(user)
                .revoked(false)
                .build();

        revokeAllTokenUser(user);
        tokenRepository.save(token);
    }

    private void revokeAllTokenUser(User user) {
        List<Token> tokens = tokenRepository.findAllValidTokenByUser(user.getId());
        tokens.forEach(token -> token.setRevoked(true));
        tokenRepository.saveAll(tokens);
    }

    public AuthenticationResponse oauth2Success(OAuth2User principal) {
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User
                            .builder()
                            .email(email)
                            .username(email)
                            .firstName(name)
                            .lastName(name)
                            .role(Role.ROLE_USER)
                            .build();
                    return userRepository.save(newUser);
                });

        String accessToken = jwtService.generateJwtToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        saveUserToken(user, refreshToken);
        return AuthenticationResponse
                .builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .timeExpiration(timeExpiration)
                .build();
    }

    public String generateLinkResetPassword(User user) {
        String token = jwtService.generateResetToken(user);
        return Constant.URL_RESET_PASSWORD + "?token=" + token;
    }

    public ApiResponse resetPassword(String token, ResetPwRequest request) {
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        if (!password.equals(confirmPassword))
            throw new BadRequestException("Password and Confirm Password must be the same");

        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return ApiResponse
                .builder()
                .success(true)
                .message("Password has been reset")
                .build();
    }

    public ApiResponse googleLogin(String idToken) {
        GoogleIdTokenVerifier verifier
                = new GoogleIdTokenVerifier
                .Builder(Utils.getDefaultTransport(), Utils.getDefaultJsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken googleIdToken;
        try {
            googleIdToken = verifier.verify(idToken);
        } catch (GeneralSecurityException | IOException e) {
            throw new BadRequestException(e.getMessage());
        }

        if (googleIdToken != null) {
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            User user;
            String email = payload.getEmail();

            if (userRepository.existsByEmail(email)) {
                user = userRepository.findByEmail(email).get();
                if (!user.getIsVerified()) {
                    user.setIsVerified(true);
                    userRepository.save(user);
                }
            } else {
                String firstName = (String) payload.get("given_name");
                String lastName = (String) payload.get("family_name");
                String avatar = (String) payload.get("picture");
                user = User
                        .builder()
                        .email(email)
                        .username(email)
                        .firstName(firstName)
                        .lastName(lastName)
                        .avatar(avatar)
                        .isVerified(true)
                        .role(Role.ROLE_USER)
                        .build();
                userRepository.save(user);
            }

            String accessToken = jwtService.generateJwtToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            AuthenticationResponse response = AuthenticationResponse
                    .builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .user(new UserDTO(user))
                    .timeExpiration(timeExpiration)
                    .build();

            return ApiResponse
                    .builder()
                    .success(true)
                    .data(response)
                    .message("Login with Google successfully")
                    .build();
        } else {
            throw new BadRequestException("Invalid ID Token");
        }
    }

    public ApiResponse facebookLogin(String accessToken) {
        String facebookGraphURL = "https://graph.facebook.com/me?fields=id,first_name,last_name,picture&access_token=" + accessToken;
        System.out.println(accessToken);
        try {
            // Verify the token by sending a request to Facebook Graph API
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForEntity(facebookGraphURL, Map.class);

            // Handle valid response
            Map<String, Object> userData = response.getBody();
            String facebookId = (String) userData.get("id");
            User user;
            if (userRepository.existsByFbId(facebookId)) {
                user = userRepository.findByFbId(facebookId).get();
            } else {
                String firstName = (String) userData.get("first_name");
                String lastName = (String) userData.get("last_name");
                String avatar = (String) ((Map<String, Object>) ((Map<String, Object>) userData.get("picture")).get("data")).get("url");

                user = User
                        .builder()
                        .fbId(facebookId)
                        .username(facebookId)
                        .firstName(firstName)
                        .lastName(lastName)
                        .avatar(avatar)
                        .role(Role.ROLE_USER)
                        .build();

                userRepository.save(user);
            }

            String jwt = jwtService.generateJwtToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            AuthenticationResponse data = AuthenticationResponse
                    .builder()
                    .refreshToken(refreshToken)
                    .accessToken(jwt)
                    .user(new UserDTO(user))
                    .timeExpiration(timeExpiration)
                    .build();

            return ApiResponse
                    .builder()
                    .success(true)
                    .data(data)
                    .message("Login with Facebook successfully")
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public ApiResponse verifyEmail(String token) {
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        if (user.getIsVerified())
            throw new BadRequestException("User is already verified");

        user.setIsVerified(true);
        userRepository.save(user);

        return ApiResponse
                .builder()
                .success(true)
                .message("User is verified")
                .build();
    }

    public String generateVerifyEmailLink(User user) {
        String token = jwtService.generateResetToken(user);
        return Constant.URL_VERIFY_EMAIL + "?token=" + token;
    }

    public ApiResponse sendResetLink(String email) throws MessagingException, IOException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));

        if (!user.getIsVerified())
            throw new BadRequestException("User with email " +  user.getEmail() +  " is not verified");

        String link = generateLinkResetPassword(user);
        emailService.sendResetLink(user.getEmail(), link);

        return ApiResponse
                .builder()
                .success(true)
                .message("Email has been sent")
                .data(link)
                .build();
    }
}
