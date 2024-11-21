package com.example.demo.user;

import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Utils utils;
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

    public ApiResponse getAllUser(Specification<User> specification, Pageable pageable) {
        Page<User> users = userRepository.findAll(specification, pageable);

        return ApiResponse.builder()
                .data(users.getContent())
                .pagination(utils.createPagination(users))
                .success(true)
                .message("Users fetched successfully")
                .build();
    }

    public ApiResponse getCurrentUserOrder(Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        return ApiResponse.builder()
                .data(user.getOrders())
                .success(true)
                .message("Orders fetched successfully")
                .build();
    }

    public ApiResponse updateProfile(User user, Principal connectedUser) {
        User currentUser = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        userRepository.save(currentUser);

        return ApiResponse.builder()
                .data(currentUser)
                .success(true)
                .message("Profile updated successfully")
                .build();
    }

    public ApiResponse getProfile(Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();

        return ApiResponse.builder()
                .data(new UserDTO(user))
                .success(true)
                .message("Profile fetched successfully")
                .build();
    }
}
