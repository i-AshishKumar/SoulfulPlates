package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.payload.request.GetOrderDetailsRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testUpdateOrderStatus() {
        // Mock data
        OrderController.UpdateOrderStatusRequest request = new OrderController.UpdateOrderStatusRequest();
        request.setOrderId(1L);
        request.setStatus("status");
        Order order = new Order();

        // Mock service method
        when(orderService.updateOrderStatus(anyLong(), anyString())).thenReturn(order);

        // Call controller method
        ResponseEntity<?> result = orderController.updateOrderStatus(request);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new MessageResponse(1, null, "Order status updated."), result.getBody());
    }

    @Test
    void testGetOrderDetails() throws Exception {
        // Mock data
        GetOrderDetailsRequest request = mock(GetOrderDetailsRequest.class);
        OrderDetailsResponse response = mock(OrderDetailsResponse.class);

        // Mock service method
        when(orderService.getOrderDetails(anyLong(), anyLong())).thenReturn(response);

        // Call controller method
        ResponseEntity<OrderDetailsResponse> result = orderController.getOrderDetails(request);

        // Assertions
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
