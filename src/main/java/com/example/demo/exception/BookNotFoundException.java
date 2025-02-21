package com.example.demo.exception;

public class BookNotFoundException extends BadRequestException{
    public BookNotFoundException(Integer id) {
        super(String.format("Book with id %d not found", id));
    }
}
