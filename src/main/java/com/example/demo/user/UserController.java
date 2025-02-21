package com.example.demo.user;

import com.example.demo.cart.CartDTO;
import com.example.demo.order.Order;
import com.example.demo.response.ApiResponse;
import com.turkraft.springfilter.boot.Filter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("order")
    public ResponseEntity<ApiResponse> getCurrentUserOrder(
            Principal connectedUser) {
        return ResponseEntity.ok(userService.getCurrentUserOrder(connectedUser));
    }

    @PostMapping("cart")
    public ResponseEntity<ApiResponse> createOrder(
            @RequestBody CartDTO cartItem,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.createCartItem(cartItem, connectedUser));
    }

    @GetMapping("cart")
    public ResponseEntity<ApiResponse> getCart(Principal connectedUser) {
        return ResponseEntity.ok(userService.getAllCartItems(connectedUser));
    }

    @PutMapping("cart/{id}")
    public ResponseEntity<ApiResponse> updateCartItem(
            @PathVariable Integer id,
            @RequestBody CartDTO cartItem,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.updateCartItem(id, cartItem, connectedUser));
    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<ApiResponse> deleteCartItem(
            @PathVariable Integer id,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.deleteCartItem(id, connectedUser));
    }

    @DeleteMapping("cart")
    public ResponseEntity<ApiResponse> deleteAllCartItems(Principal connectedUser) {
        return ResponseEntity.ok(userService.deleteAllCartItems(connectedUser));
    }

    @PutMapping("")
    public ResponseEntity<ApiResponse> updateProfile(
            @RequestBody User user,
            Principal connectedUser) {
        return ResponseEntity.ok(userService.updateProfile(user, connectedUser));
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse> getProfile(Principal connectedUser) {
        return ResponseEntity.ok(userService.getProfile(connectedUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("verify-email")
    public ResponseEntity<ApiResponse> sendRequestVerifyEmail(Principal connectedUser) throws MessagingException, IOException {
        return ResponseEntity.ok(userService.sendRequestVerifyEmail(connectedUser));
    }

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder
            (@RequestBody Order order) {
        return ResponseEntity.ok(userService.createOrder(order));
    }
}
