package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private boolean success;
    private String message;
    private Pagination pagination;
    private Object data;
}
