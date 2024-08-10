package com.example.demo.category;

import com.example.demo.response.ApiResponse;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<ApiResponse> getAllCategory
        (@Filter Specification<Category> specification, Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategory(specification, pageable));
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
