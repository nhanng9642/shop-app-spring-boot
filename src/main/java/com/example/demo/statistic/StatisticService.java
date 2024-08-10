package com.example.demo.statistic;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.order.OrderDetailRepository;
import com.example.demo.order.OrderRepository;
import com.example.demo.response.ApiResponse;
import com.example.demo.user.UserRepository;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final Utils utils;
    private final UserRepository userRepository;

    public ApiResponse getTopBestSellingBook(Pageable pageable) {
        Page<BookStatistic> books = orderDetailRepository.findTopBestSellingBook(pageable);

        return ApiResponse.builder()
                .data(books.getContent())
                .success(true)
                .pagination(utils.createPagination(books))
                .message("Top best selling books")
                .build();
    }

    public ApiResponse getTotalRevenue() {
        Long totalRevenue = orderRepository.getTotalRevenue();

        return ApiResponse.builder()
                .data(totalRevenue)
                .success(true)
                .message("Total revenue")
                .build();
    }

    public ApiResponse getTotalUser() {
        Long totalUser = userRepository.count();

        return ApiResponse.builder()
                .data(totalUser)
                .success(true)
                .message("Total user")
                .build();
    }

    public ApiResponse getTotalBook() {
        Long totalBook = bookRepository.count();

        return ApiResponse.builder()
                .data(totalBook)
                .success(true)
                .message("Total book")
                .build();
    }

    public ApiResponse getTotalOrder() {
        Long totalOrder = orderRepository.count();

        return ApiResponse.builder()
                .data(totalOrder)
                .success(true)
                .message("Total order")
                .build();
    }

    public ApiResponse getMonthlyRevenue(Pageable pageable) {
        List<Object[]> monthlyRevenues = orderRepository.getMonthlyRevenueStatistic(pageable);

        return ApiResponse.builder()
                .data(monthlyRevenues)
                .success(true)
                .message("Monthly revenue")
                .build();
    }
}
