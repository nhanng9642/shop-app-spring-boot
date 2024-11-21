package com.example.demo.book;

import com.example.demo.category.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Author is required")
    private String author;

    private String publisher;

    @NotNull(message = "Published year is required")
    @Positive(message = "Published year must be greater than 0")
    private Integer publishedYear;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Float price;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantityAvailable;

    private String description;

    private MultipartFile bookImage;

    @NotNull(message = "Category id is required")
    @Positive(message = "Category id is invalid")
    private Integer categoryId;
}
