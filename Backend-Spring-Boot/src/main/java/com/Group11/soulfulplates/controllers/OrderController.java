package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.request.GetOrderDetailsRequest;
import com.Group11.soulfulplates.payload.request.GetOrdersRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.payload.response.OrdersResponse;
import com.Group11.soulfulplates.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getDetails")
    @PreAuthorize("hasRole('ROLE_BUYER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@RequestBody GetOrderDetailsRequest request) {
        try {
            OrderDetailsResponse response = orderService.getOrderDetails(request.getUserId(), request.getOrderId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1, "Error getting order details: " + e.getMessage(), null));
        }
    }

    @GetMapping("/getForUser")
    public ResponseEntity getOrdersForUser(@RequestBody GetOrdersRequest request) {
        try {
            OrdersResponse response = orderService.getOrdersForUser(request.getUserId(), request.getStatus(), request.getLimit(), request.getOffset());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrderDetailsResponse(-1, "Error getting order details: " + e.getMessage(), null));
        }
    }

}
