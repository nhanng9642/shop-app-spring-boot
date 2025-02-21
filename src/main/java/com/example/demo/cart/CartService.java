package com.example.demo.cart;

import com.example.demo.book.BookService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.BookNotFoundException;
import com.example.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final BookService bookService;

    public void save(User user, CartDTO cartDTO) {
        Cart cart = new Cart();
        var bookId = cartDTO.getBookId();

        cart.setBook(bookService.getBook(bookId));
        cart.setUser(user);
        cart.setQuantity(cartDTO.getQuantity());

        cartRepository.save(cart);
    }

    public void update(User user, Integer bookId, CartDTO cartDTO) {
        Cart cart = cartRepository.findByUserAndBookId(user, bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        cart.setQuantity(cartDTO.getQuantity());
        cartRepository.save(cart);
    }

    public void deleteCartItem(User user, Integer bookId) {
        Cart cart = cartRepository.findByUserAndBookId(user, bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        cartRepository.delete(cart);
    }

    public void deleteAllCartItems(User user) {
        cartRepository.deleteAllByUser(user);
    }
}
