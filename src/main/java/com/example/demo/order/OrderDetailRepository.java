package com.example.demo.order;

import com.example.demo.book.Book;
import com.example.demo.statistic.BookStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {
    @Query("SELECT new com.example.demo.statistic.BookStatistic(b.title, b.author, b.publisher, SUM(od.quantity)) " +
            "FROM order_detail od " +
            "JOIN od.book b " +
            "GROUP BY b.id " +
            "ORDER BY SUM(od.quantity) DESC")
    Page<BookStatistic> findTopBestSellingBook(Pageable pageable);
}
