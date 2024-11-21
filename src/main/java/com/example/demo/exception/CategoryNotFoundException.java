package com.example.demo.exception;

public class CategoryNotFoundException extends BadRequestException {
    public CategoryNotFoundException(Integer id) {
        super("Category with id " + id + " not found!");
    }
}
