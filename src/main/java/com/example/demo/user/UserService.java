package com.example.demo.user;

import com.cloudinary.Api;
import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.EmailService;
import com.example.demo.cart.CartDTO;
import com.example.demo.cart.CartService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.order.Order;
import com.example.demo.order.OrderService;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthenticationService authenticationService;
    private final CartService cartService;
    private final OrderService orderService;

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

    public ApiResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id));

        return ApiResponse
                .builder()
                .message("Get user success")
                .data(new UserDTO(user))
                .success(true)
                .build();
    }

    public ApiResponse sendRequestVerifyEmail(Principal connectedUser) throws MessagingException, IOException {
        User user = userRepository.findByUsername(connectedUser.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getIsVerified())
            throw new BadRequestException("User is already verified");

        String link = authenticationService.generateVerifyEmailLink(user);
        emailService.sendVerifyEmail(user.getEmail(), link);

        return ApiResponse
                .builder()
                .success(true)
                .data(link)
                .message("Send verify email success")
                .build();
    }

    public ApiResponse createCartItem(CartDTO cartItem, Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        cartService.save(user, cartItem);

        return ApiResponse.builder()
                .success(true)
                .data(cartItem)
                .message("Add book to cart success!")
                .build();
    }

    public ApiResponse updateCartItem
            (Integer bookId, CartDTO cartItem, Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        cartService.update(user, bookId, cartItem);

        return ApiResponse.builder()
                .success(true)
                .data(cartItem)
                .message("Update cart item success!")
                .build();
    }

    public ApiResponse deleteCartItem(Integer bookId, Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        cartService.deleteCartItem(user, bookId);

        return ApiResponse.builder()
                .success(true)
                .message("Delete cart item success!")
                .build();
    }

    public ApiResponse deleteAllCartItems(Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        cartService.deleteAllCartItems(user);

        return ApiResponse.builder()
                .success(true)
                .message("Delete all cart items success!")
                .build();
    }

    public ApiResponse getAllCartItems(Principal connectedUser) {
        User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
        return ApiResponse.builder()
                .success(true)
                .data(user.getCarts())
                .message("Get all cart items success!")
                .build();
    }

    public ApiResponse createOrder(Order order) {
        return orderService.createOrder(order);
    }
}
