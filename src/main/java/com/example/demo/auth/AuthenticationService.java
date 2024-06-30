package com.example.demo.auth;

import com.example.demo.exception.BadRequestException;
import com.example.demo.security.JwtService;
import com.example.demo.token.Token;
import com.example.demo.token.TokenRepository;
import com.example.demo.user.Role;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
