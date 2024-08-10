package com.example.demo.utils;

import com.example.demo.exception.BadRequestException;
import com.example.demo.response.Pagination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class Utils {
    @Value("${default.page:0}")
    private int defaultPage;

    @Value("${default.size:10}")
    private int defaultSize;

    @Value("${default.sort:id}")
    private String defaultSortField = "id";

    @Value("${default.sort.direction:ASC}")
    private String defaultSortDirection;

    public PageRequest createPageRequest(
            Integer pageOptional,
            Integer sizeOptional,
            String sortOptional) {
        int page = pageOptional != null ? pageOptional : defaultPage;
        int size = sizeOptional != null ? sizeOptional : defaultSize;
        String sortDirection = defaultSortDirection;
        String sortField = defaultSortField;

        if (sortOptional != null) {
            String[] sort = sortOptional.split(",");
            sortField = sort[0];
            if (sort.length > 1) {
                sortDirection = sort[1];
            }
        }

        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than 0");
        }
        return PageRequest.of(page, size,
                Sort.by(Sort.Direction.fromString(sortDirection), sortField));
    }

    public Pagination createPagination(Page<?> page) {
        return Pagination.builder()
                .totalRecords((int) page.getTotalElements())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .nextPage(page.hasNext() ? page.getNumber() + 1 : null)
                .prevPage(page.hasPrevious() ? page.getNumber() - 1 : null)
                .build();
    }
}
