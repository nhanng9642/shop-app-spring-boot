package com.example.demo.user;

import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.changePassword(request, connectedUser));
    }
}
