package com.example.demo.statistic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class MonthlyRevenue {
    private int month;
    private int year;
    private double total;
}
