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
            Integer page,
            Integer size,
            String sort
    ) {
        PageRequest pageRequest = utils.createPageRequest(page, size, sort);
        Page<Order> orders = orderRepository.findAll(pageRequest);

        return ApiResponse
                .builder()
                .success(true)
                .pagination(utils.createPagination(orders))
                .data(orders.getContent())
                .message("Get all orders success!")
                .build();
    }

    public ApiResponse getAllByUser(
            Integer page,
            Integer size,
            String sort
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        PageRequest pageRequest = utils.createPageRequest(page, size, sort);
        Page<Order> orders = orderRepository.findAllByUserId(pageRequest, user.getId());

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

        order.setUserId(user.getId());
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
                throw new BadRequestException("Book quantity not enough!");
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
