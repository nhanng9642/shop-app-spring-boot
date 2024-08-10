package com.example.demo.user;

import com.example.demo.response.ApiResponse;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUser
            (@Filter Specification<User> specification, Pageable pageable){
        return ResponseEntity.ok(userService.getAllUser(specification, pageable));
    }

    @GetMapping("/me/order")
    public ResponseEntity<ApiResponse> getCurrentUserOrder(
            Principal connectedUser) {
        return ResponseEntity.ok(userService.getCurrentUserOrder(connectedUser));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse> updateProfile(
            @RequestBody User user,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.updateProfile(user, connectedUser));
    }
}
