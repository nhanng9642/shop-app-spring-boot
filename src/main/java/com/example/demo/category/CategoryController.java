package com.example.demo.category;

import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllCategory
        (@RequestParam(required = false) Integer page,
         @RequestParam(required = false) Integer size,
         @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(categoryService.getAllCategory(page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category ) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory
            (@PathVariable Integer id,
             @RequestBody Category category
            ) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }
}
