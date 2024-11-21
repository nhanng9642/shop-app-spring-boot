package com.example.demo.auth;
import com.example.demo.user.UserDTO;
import lombok.*;

@Data
@Builder
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private int timeExpiration;
    private UserDTO user;
}
