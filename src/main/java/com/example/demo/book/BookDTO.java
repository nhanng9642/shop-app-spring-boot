package com.example.demo.book;

import com.example.demo.category.Category;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String publisher;
    private String publishedYear;
    private float price;
    private Integer quantityAvailable;
    private String description;
    private MultipartFile bookImage;
    private Integer categoryId;
}
