package com.example.demo.order;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.exception.BadRequestException;
import com.example.demo.response.ApiResponse;
import com.example.demo.user.User;
import com.example.demo.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final BookRepository bookRepository;
    private final Utils utils;

    public ApiResponse getAllOrder(
           Specification<Order> specification, Pageable pageable
    ) {
        Page<Order> orders = orderRepository.findAll(specification, pageable);

        return ApiResponse
                .builder()
                .success(true)
                .pagination(utils.createPagination(orders))
                .data(orders.getContent())
                .message("Get all orders success!")
                .build();
    }

    @Transactional
    public ApiResponse createOrder(Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        order.setUser(user);
        order.setStatus("PENDING");
        order.setTotal(0);
        order.setOrderDate(new Date());

        Order savedOrder = orderRepository.save(order);

        List<OrderDetail> orderDetails = order.getOrderDetails();
        System.out.println(savedOrder.getStatus());

        float total = 0;
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrder(savedOrder);
            int quantity = orderDetail.getQuantity();
            Book book = bookRepository.findById(orderDetail.getBook().getId())
                    .orElseThrow(() -> new BadRequestException("Book not found!"));

            if (book.getQuantityAvailable() < quantity) {
                throw new BadRequestException(String.format("Book with ID - %d not enough quantity!", book.getId()));
            }

            book.setQuantityAvailable(book.getQuantityAvailable() - quantity);
            bookRepository.save(book);
            orderDetail.setTotal(orderDetail.getPrice() * orderDetail.getQuantity());
            total += orderDetail.getTotal();
        }

        savedOrder.setTotal(total);
        orderDetailRepository.saveAll(orderDetails);
        orderRepository.save(savedOrder);

        return ApiResponse
                .builder()
                .success(true)
                .data(savedOrder)
                .message("Create order success!")
                .build();
    }

    public ApiResponse getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Order not found!"));

        return ApiResponse
                .builder()
                .success(true)
                .data(order)
                .message("Get order by id success!")
                .build();
    }

    public ApiResponse updateOrderStatus(Integer id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Order not found!"));

        order.setStatus(status);
        orderRepository.save(order);

        return ApiResponse
                .builder()
                .success(true)
                .data(order)
                .message("Update order status success!")
                .build();
    }
}
