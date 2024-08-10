package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pagination {
    private Integer totalRecords;
    private Integer currentPage;
    private Integer totalPages;
    private Integer nextPage;
    private Integer prevPage;
    private Integer pageSize;
}
