package com.example.demo.order;

import com.example.demo.customer.Customer;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "sale_order")
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String orderDate;
    private float total;
    private String status;
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
