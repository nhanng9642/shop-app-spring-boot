package com.example.demo.auth;

import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtService;
import com.example.demo.token.Token;
import com.example.demo.token.TokenRepository;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Value("${application.security.jwt.expiration}")
    private int timeExpiration;

    public AuthenticationResponse login(@Valid LoginRequest request) {
        String username = request.getUsername();

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        request.getPassword()
                )
        );

        User user = userRepository.findUserByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Email not found"));

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

    public AuthenticationResponse register(@Valid RegisterRequest request){
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

        return AuthenticationResponse
                .builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .timeExpiration(timeExpiration)
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

    public String generateLinkResetPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        String token = jwtService.generateResetToken(user);
        return Constant.URL_SERVER + "/api/v1/auth/reset-password?token=" + token;
    }

    public ApiResponse resetPassword(String token, ResetPwRequest request) {
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));

        if (!password.equals(confirmPassword))
            throw new BadRequestException("Password and Confirm Password must be the same");

        if (jwtService.isTokenExpired(token))
            throw new BadRequestException("Token is expired");

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return ApiResponse
                .builder()
                .success(true)
                .message("Password has been reset")
                .build();
    }
}
