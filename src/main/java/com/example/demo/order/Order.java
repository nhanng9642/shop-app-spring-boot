package com.example.demo.order;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString(exclude = {"orderDetails", "user"})
@Entity(name = "sale_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date orderDate;

    private float total;

    private String status;

    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Transient
    public User user;

    @Column(name = "user_id")
    private Integer userId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    public List<OrderDetail> orderDetails;
}
