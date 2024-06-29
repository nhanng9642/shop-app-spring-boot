package com.example.demo.auth;
import lombok.*;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private int timeExpiration;
}
