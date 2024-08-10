package com.example.demo.order;

import com.example.demo.response.ApiResponse;
import com.turkraft.springfilter.boot.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllOrders(
            @Filter Specification<Order> specification, Pageable pageable
    ) {
        return ResponseEntity.ok(orderService.getAllOrder(specification, pageable));
    }

    @PostMapping("/me/cart")
    public ResponseEntity<ApiResponse> createOrder
            (@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateOrderByStatus(
            @PathVariable Integer id,
            @RequestBody Order order
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, order.getStatus()));
    }
}
