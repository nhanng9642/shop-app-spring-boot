package com.example.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private int status;
    private Date timestamp = new Date();
    private String message;
}
