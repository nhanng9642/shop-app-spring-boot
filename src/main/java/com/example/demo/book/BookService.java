package com.example.demo.book;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.CloudinaryService;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final Utils utils;

    @Value("${application.default.image}")
    private String defaultBookImage;

    private final CloudinaryService cloudinaryService;

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
        checkExistedCategory(bookDTO.getCategoryId());
        Category category = categoryRepository.findById(bookDTO.getCategoryId()).orElse(null);
        if (bookDTO.getBookImage() != null)
            bookImage = cloudinaryService.uploadFile(bookDTO.getBookImage());

        Book book = Book
                .builder()
                .author(bookDTO.getAuthor())
                .description(bookDTO.getDescription())
                .price(bookDTO.getPrice())
                .publishedYear(bookDTO.getPublishedYear())
                .title(bookDTO.getTitle())
                .quantityAvailable(bookDTO.getQuantityAvailable())
                .bookImage(bookImage)
                .publisher(bookDTO.getPublisher())
                .category(category)
                .build();

        return book;
    }

    private void checkExistedCategory(Integer categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty())
            throw new BadRequestException("Category is not found!");
    }

    private void checkExistedBook(Integer id) {
        if (bookRepository.findById(id).isEmpty())
            throw new BadRequestException("Book not found");
    }
}
