package com.example.demo.auth;

import lombok.Data;

@Data
public class ResetPwRequest {
    private String password;
    private String confirmPassword;
}
