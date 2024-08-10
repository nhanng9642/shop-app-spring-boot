package com.example.demo.book;

import com.example.demo.response.ApiResponse;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllBooks
            (@Filter Specification<Book> specification, Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(specification, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ApiResponse> addBook(
            @ModelAttribute BookDTO bookDTO
            ) {
        return ResponseEntity.ok(bookService.createBook(bookDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateBook(
            @PathVariable Integer id,
            @ModelAttribute BookDTO bookDTO
    ) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBook(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }
}
