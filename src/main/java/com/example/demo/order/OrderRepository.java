package com.example.demo.order;

import com.example.demo.statistic.MonthlyRevenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("SELECT SUM(s.total) FROM sale_order s")
    Long getTotalRevenue();

    @Query
            ("SELECT new com.example.demo.statistic.MonthlyRevenue(MONTH(s.orderDate), YEAR(s.orderDate), SUM(s.total)) " +
            "FROM sale_order s " +
            "GROUP BY MONTH(s.orderDate), YEAR(s.orderDate) " +
            "ORDER BY YEAR(s.orderDate), MONTH(s.orderDate)")
    Page<MonthlyRevenue> getMonthlyRevenueStatistic(Pageable pageable);
}
