package com.example.demo.auth;

import com.example.demo.response.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/sign-up")
    ResponseEntity<ApiResponse> signIn(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh-token")
    ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @GetMapping("oauth2/success")
    ResponseEntity<AuthenticationResponse> oauth2Success
            (@AuthenticationPrincipal OAuth2User principal) {
        return ResponseEntity.ok(authService.oauth2Success(principal));
    }

    @GetMapping("/recover-password")
    ResponseEntity<ApiResponse> sendEmailToResetPw(@RequestParam String email)
            throws MessagingException, IOException {
        return ResponseEntity.ok(authService.sendResetLink(email));
    }

    @PostMapping("/reset-password")
    ResponseEntity<ApiResponse> resetPassword(@RequestParam String token,
                                              @RequestBody ResetPwRequest request
    ) {
        return ResponseEntity.ok(authService.resetPassword(token, request));
    }

    @PostMapping("/oauth2/google")
    ResponseEntity<ApiResponse> googleLogin(@RequestBody CredentialDTO credential) {
        return ResponseEntity.ok(authService.googleLogin(credential.getCredential()));
    }

    @PostMapping("/oauth2/facebook")
    ResponseEntity<ApiResponse> facebookLogin(@RequestBody CredentialDTO credential) {
        return ResponseEntity.ok(authService.facebookLogin(credential.getAccessToken()));
    }

    @GetMapping("/verify-email")
    ResponseEntity<ApiResponse> verifyEmail(@RequestParam String token) {
        return ResponseEntity.ok(authService.verifyEmail(token));
    }
}
