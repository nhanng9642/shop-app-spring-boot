package com.example.demo.test;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.order.OrderDTO;
import com.example.demo.order.OrderRepository;
import com.example.demo.utils.CloudinaryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final StorageService storageService;
    private final CloudinaryService cloudinaryService;
    private final BookRepository bookRepository;

    @GetMapping("/file/{filename}")
    String getFileName(@PathVariable String filename) {
        return filename;
    }

    @PostMapping("/file")
    String handleUploadFile(@RequestParam MultipartFile file) {
        storageService.store(file);
        return file.getName() + "save success";
    }

    @PostMapping("/file-cloud")
    String handleUploadCloudinary(@RequestParam MultipartFile file) {
        return cloudinaryService.uploadFile(file);
    }

    @GetMapping("/upload-all-file")
    String upload() throws IOException {
        String dir = "file-upload/books";
        File dirPath = new File(dir);

        File[] files = dirPath.listFiles();
        try {
            for (File file : files) {
                String filename = file.getName();
                System.out.println(filename);
                String fileUpload = cloudinaryService.uploadFile(file);
                Optional<Book> bookOption = bookRepository.findByBookImage(filename);
                if (bookOption.isEmpty()) {
                    System.out.println("NO BOOK: " + filename);
                    continue;
                }
                Book book = bookOption.get();
                book.setBookImage(fileUpload);
                bookRepository.save(book);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
