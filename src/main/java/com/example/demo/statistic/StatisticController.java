package com.example.demo.statistic;

import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/best-selling-books")
    public ResponseEntity<ApiResponse> getTopBestSellingBook(Pageable pageable) {
        return ResponseEntity.ok(statisticService.getTopBestSellingBook(pageable));
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<ApiResponse> getTotalRevenue() {
        return ResponseEntity.ok(statisticService.getTotalRevenue());
    }

    @GetMapping("/total-user")
    public ResponseEntity<ApiResponse> getTotalUser() {
        return ResponseEntity.ok(statisticService.getTotalUser());
    }

    @GetMapping("/total-book")
    public ResponseEntity<ApiResponse> getTotalBook() {
        return ResponseEntity.ok(statisticService.getTotalBook());
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<ApiResponse> getMonthlyRevenueStatistic(Pageable pageable) {
        return ResponseEntity.ok(statisticService.getMonthlyRevenue(pageable));
    }
}
