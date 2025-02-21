package com.example.demo.exception;

public class UserNotFoundException extends BadRequestException{
    public UserNotFoundException(Integer id) {
        super(String.format("User with id %d not found", id));
    }
}
