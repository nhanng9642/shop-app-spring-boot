package com.example.demo.book;

import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(bookService.getAllBooks(page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getBookById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse> getBookByCategory(
            @PathVariable Integer categoryId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(bookService.getBookByCategory(categoryId, page, size, sort));
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
