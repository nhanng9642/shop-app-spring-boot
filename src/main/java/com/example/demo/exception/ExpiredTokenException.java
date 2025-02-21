package com.example.demo.exception;

public class ExpiredTokenException extends BadRequestException{
    public ExpiredTokenException(long time) {
        super("Token expired at " + time + "s before");
    }
}
