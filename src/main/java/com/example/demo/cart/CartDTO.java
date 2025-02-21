package com.example.demo.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CartDTO {
    private Integer quantity;
    private Integer bookId;

    public CartDTO(Cart cart) {
        this.quantity = cart.getQuantity();
        this.bookId = cart.getBook().getId();
    }
}
