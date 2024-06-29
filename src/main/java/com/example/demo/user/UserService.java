package com.example.demo.user;

import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse changePassword(ChangePasswordRequest request, Principal connectedUser) {
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();

        boolean isMatch = passwordEncoder.matches(request.getCurrentPassword(), user.getPassword());
        if (!isMatch) {
            throw new BadRequestException("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ApiResponse.builder()
                .data(null)
                .success(true)
                .message("Password changed successfully")
                .build();
    }
}
