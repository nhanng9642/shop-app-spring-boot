package com.example.demo.auth;

import com.example.demo.response.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;
    private final EmailService emailService;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/sign-in")
    ResponseEntity<AuthenticationResponse> signIn(@RequestBody RegisterRequest request) {
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
        String linkReset = authService.generateLinkResetPassword(email);
        return ResponseEntity.ok(emailService.sendResetLink(email, linkReset));
    }

    @PostMapping("/reset-password")
    ResponseEntity<ApiResponse> resetPassword(@RequestParam String token,
                                              @RequestBody ResetPwRequest request
    ) {
        return ResponseEntity.ok(authService.resetPassword(token, request));
    }
}
