package com.example.demo.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookStatistic {
    private String title;
    private String author;
    private String publisher;
    private Long totalQuantity;
}
