package com.example.demo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalException {
    Logger log = org.slf4j.LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler({BadRequestException.class})
    ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage(e.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(401);
        errorResponse.setMessage(e.getMessage());
        return ResponseEntity.status(401).body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<ErrorResponse> handleNoResourceFoundException
            (NoResourceFoundException e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(404);
        errorResponse.setMessage(e.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(500);
        errorResponse.setMessage(e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }


}
