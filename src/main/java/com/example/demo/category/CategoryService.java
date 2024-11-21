package com.example.demo.category;

import com.example.demo.book.BookRepository;
import com.example.demo.book.BookService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final Utils utils;

    public ApiResponse getAllCategory
            (Specification<Category> specification, Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(specification, pageable);

        return ApiResponse
                .builder()
                .success(true)
                .message("Get categories success!")
                .data(categories.getContent())
                .pagination(utils.createPagination(categories))
                .build();
    }

    public ApiResponse getCategoryById(Integer id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        return ApiResponse
                .builder()
                .success(true)
                .message("Get categories success!")
                .data(category)
                .build();
    }

    public ApiResponse createCategory(Category category) {
        Category newCategory = categoryRepository.save(category);

        return ApiResponse
                .builder()
                .success(true)
                .message("Save categories success!")
                .data(newCategory)
                .build();
    }

    public ApiResponse updateCategory(Integer id, Category category) {
        if (!categoryRepository.existsById(id))
            throw new BadRequestException("Category not found");

        category.setId(id);
        Category newCategory = categoryRepository.save(category);

        return ApiResponse
                .builder()
                .success(true)
                .message("Update category success!")
                .data(newCategory)
                .build();
    }

    public ApiResponse deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id))
            throw new CategoryNotFoundException(id);

        if (bookRepository.existsBookByCategoryId(id))
            throw new BadRequestException(String.format("Cannot delete category with id %d. It is being used by books", id));

        categoryRepository.deleteById(id);

        return ApiResponse
                .builder()
                .success(true)
                .message("Delete category success!")
                .build();
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
