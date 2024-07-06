package com.example.demo.category;

import com.cloudinary.Api;
import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Utils utils;

    public ApiResponse getAllCategory
            (Integer page, Integer size, String sort) {

        Page<Category> categories = categoryRepository.findAll(utils.createPageRequest(page, size, sort));

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
                .message("Update categories success!")
                .data(newCategory)
                .build();
    }

}
