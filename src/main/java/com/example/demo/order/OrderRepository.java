package com.example.demo.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("SELECT SUM(s.total) FROM sale_order s")
    Long getTotalRevenue();

    @Query("SELECT month (s.orderDate) as month," +
            " SUM(s.total) as total FROM sale_order s GROUP BY month")
    List<Object[]> getMonthlyRevenueStatistic(Pageable pageable);
}
