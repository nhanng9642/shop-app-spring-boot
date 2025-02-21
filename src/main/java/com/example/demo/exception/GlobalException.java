package com.example.demo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalException {
    Logger log = org.slf4j.LoggerFactory.getLogger(GlobalException.class);

    private void logError(Exception e) {
        log.error("Exception: {} - {}", e.getClass().getSimpleName(), e.getMessage(), e);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(403, e.getMessage());
        return ResponseEntity.status(403).body(errorResponse);
    }

    @ExceptionHandler(TypeMismatchException.class)
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(TypeMismatchException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler({BadRequestException.class})
    ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(400, e.getBindingResult().getFieldError().getDefaultMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(401, e.getMessage());
        return ResponseEntity.status(401).body(errorResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(404, e.getMessage());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    ResponseEntity<ErrorResponse> handleExpiredTokenException(ExpiredJwtException e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleException(Exception e) {
        logError(e);
        ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
        return ResponseEntity.status(500).body(errorResponse);
    }
}
