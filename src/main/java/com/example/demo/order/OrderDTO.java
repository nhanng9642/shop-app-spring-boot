package com.example.demo.order;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Integer id;

    private List<OrderDetail> orderDetails;

    private Integer userId;

    private float total;
}
