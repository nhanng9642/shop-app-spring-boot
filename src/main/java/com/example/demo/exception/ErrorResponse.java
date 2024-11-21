package com.example.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private int status;
    private boolean success = false;
    private Date timestamp = new Date();
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
