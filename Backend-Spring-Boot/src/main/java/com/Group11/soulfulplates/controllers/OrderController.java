package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody(required = false) CreateOrderRequest request) {
        CreateOrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestBody(required = false) UpdateOrderStatusRequest request) {
        Order order = orderService.updateOrderStatus(request.getOrderId(), request.getStatus());
        if(order != null) {
            return ResponseEntity.ok(new MessageResponse(1, null, "Order status updated."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(-1, null, "Error updating order status."));
        }
    }

    static class UpdateOrderStatusRequest {
        private Long orderId;
        private String status;

        // Getters and Setters
        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
