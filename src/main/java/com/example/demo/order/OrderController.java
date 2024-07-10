package com.example.demo.order;

import com.example.demo.response.ApiResponse;
import lombok.RequiredArgsConstructor;
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
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(orderService.getAllOrder(page, size, sort));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getAllOrdersByUser(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sort
    ) {
        return ResponseEntity.ok(orderService.getAllByUser(page, size, sort));
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
