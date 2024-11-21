package com.example.demo.book;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.category.CategoryService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.CloudinaryService;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final Utils utils;
    private final CloudinaryService cloudinaryService;

    @Value("${application.default.image}")
    private String defaultBookImage;

    public ApiResponse getAllBooks(Specification<Book> specification, Pageable pageable) {
        Page<Book> books = bookRepository.findAll(specification, pageable);

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

    public ApiResponse createBook(BookDTO bookDTO) {
        Book newBook = bookRepository.save(convertToBook(bookDTO));
        return ApiResponse.builder()
                .success(true)
                .data(newBook)
                .message("Create book success!")
                .build();
    }

    public ApiResponse updateBook(Integer id, BookDTO bookDTO) {
        Book book = convertToBook(bookDTO);
        checkExistedBook(id);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);

        return ApiResponse.builder()
                .success(true)
                .data(updatedBook)
                .message("Update book success!")
                .build();
    }

    public ApiResponse deleteBook(Integer id) {
        checkExistedBook(id);
        bookRepository.deleteById(id);

        return ApiResponse.builder()
                .success(true)
                .data(null)
                .message("Delete book success!")
                .build();
    }

    private Book convertToBook(BookDTO bookDTO) {
        String bookImage = defaultBookImage;

        int categoryId = bookDTO.getCategoryId();
        Category category = categoryService.getCategory(categoryId);

        if (bookDTO.getBookImage() != null)
            bookImage = cloudinaryService.uploadFile(bookDTO.getBookImage());

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublisher(bookDTO.getPublisher());
        book.setPublishedYear(bookDTO.getPublishedYear());
        book.setPrice(bookDTO.getPrice());
        book.setQuantityAvailable(bookDTO.getQuantityAvailable());
        book.setDescription(bookDTO.getDescription());
        book.setBookImage(bookImage);
        book.setCategory(category);

        return book;
    }

    private void checkExistedBook(Integer id) {
        if (bookRepository.findById(id).isEmpty())
            throw new BadRequestException("Book not found");
    }
}
