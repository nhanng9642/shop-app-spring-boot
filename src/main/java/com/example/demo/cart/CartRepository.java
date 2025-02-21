package com.example.demo.cart;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUserAndBookId(User user, Integer bookId);
    void deleteAllByUser(User user);
}
