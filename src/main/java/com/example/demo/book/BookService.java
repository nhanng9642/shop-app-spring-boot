package com.example.demo.book;

import com.example.demo.category.Category;
import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final Utils utils;

    public ApiResponse getAllBooks(Integer page, Integer size, String sort) {
        PageRequest pageRequest = utils.createPageRequest(page, size, sort);
        Page<Book> books = bookRepository.findAll(pageRequest);

        return ApiResponse.builder()
                .success(true)
                .data(books.getContent())
                .pagination(utils.createPagination(books))
                .message("Get all books success!")
                .build();
    }

    public ApiResponse getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Book not found!"));

        return ApiResponse.builder()
                .success(true)
                .data(book)
                .message("Get book by id success!")
                .build();
    }

    public ApiResponse getBookByCategory(Integer categoryId, Integer page, Integer size, String sort) {
        PageRequest pageRequest = utils.createPageRequest(page, size, sort);
        Page<Book> books = bookRepository.findBooksByCategoryId(categoryId, pageRequest);

        return ApiResponse.builder()
                .success(true)
                .data(books.getContent())
                .pagination(utils.createPagination(books))
                .message("Get books by category success!")
                .build();



    }

    public ApiResponse createBook(Book book) {
        Book newBook = bookRepository.save(book);

        return ApiResponse.builder()
                .success(true)
                .data(newBook)
                .message("Create book success!")
                .build();
    }

    public ApiResponse updateBook(Integer id, Book book) {
        Book updatedBook = bookRepository.findById(id)
                .map(b -> {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                    b.setPrice(book.getPrice());
                    b.setCategory(book.getCategory());
                    return bookRepository.save(b);
                })
                .orElseThrow(() -> new BadRequestException("Book not found!"));

        return ApiResponse.builder()
                .success(true)
                .data(updatedBook)
                .message("Update book success!")
                .build();
    }
}
