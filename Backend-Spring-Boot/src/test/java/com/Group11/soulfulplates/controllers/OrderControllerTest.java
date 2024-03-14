//package com.Group11.soulfulplates.controllers;
//
//import com.Group11.soulfulplates.controllers.OrderController;
//import com.Group11.soulfulplates.models.Order;
//import com.Group11.soulfulplates.payload.response.MessageResponse;
//import com.Group11.soulfulplates.services.OrderService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class OrderControllerTest {
//
//    @InjectMocks
//    private OrderController OrderController;
//
//    @Mock
//    private OrderService orderService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getOrdersForStore_ValidRequest_ReturnsOrders() {
//        // Given
//        long storeId = 1L;
//        String status = "Completed";
//        int limit = 20;
//        int offset = 0;
//
//        List<Order> orders = new ArrayList<>();
//
//
//        when(orderService.getOrdersForStore(storeId, status, limit, offset)).thenReturn(orders);
//
//        // When
//        ResponseEntity<MessageResponse> responseEntity = com.Group11.soulfulplates.controllers.OrderController.getOrdersForStore(storeId, status, limit, offset);
//
//        // Then
//        assertEquals(200, responseEntity.getStatusCodeValue());
//
//        MessageResponse response = responseEntity.getBody();
//        assertEquals(1, response.getCode());
//        assertEquals("Success", response.getDescription());
//        // assertEquals(expectedData, response.getData());
//
//        verify(orderService, times(1)).getOrdersForStore(storeId, status, limit, offset);
//    }
//
//    @Test
//    void getOrdersForStore_InvalidStoreId_ReturnsErrorMessage() {
//        // Given
//        long storeId = 1L; // Invalid store ID
//        String status = "Completed";
//        int limit = 20;
//        int offset = 0;
//
//        when(orderService.getOrdersForStore(storeId, status, limit, offset)).thenReturn(null);
//
//        // When
//        ResponseEntity<MessageResponse> responseEntity = com.Group11.soulfulplates.controllers.OrderController.getOrdersForStore(storeId, status, limit, offset);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(404, responseEntity.getStatusCodeValue());
//
//        MessageResponse response = responseEntity.getBody();
//        assertNotNull(response);
//        assertEquals(0, response.getCode());
//        assertEquals("No orders found for store with ID: " + storeId, response.getDescription());
//
//        verify(orderService, times(1)).getOrdersForStore(storeId, status, limit, offset);
//    }
//
//    @Test
//    void getOrdersForStore_InvalidStatus_ReturnsErrorMessage() {
//        // Given
//        long storeId = 1L;
//        String status = "InvalidStatus"; // Invalid status
//        int limit = 20;
//        int offset = 0;
//
//        when(orderService.getOrdersForStore(storeId, status, limit, offset)).thenReturn(null);
//
//        // When
//        ResponseEntity<MessageResponse> responseEntity = com.Group11.soulfulplates.controllers.OrderController.getOrdersForStore(storeId, status, limit, offset);
//
//        // Then
//        assertNotNull(responseEntity);
//        assertEquals(400, responseEntity.getStatusCodeValue());
//
//        MessageResponse response = responseEntity.getBody();
//        assertNotNull(response);
//        assertEquals(0, response.getCode());
//        assertEquals("Invalid status provided: " + status, response.getDescription());
//
//        verify(orderService, times(1)).getOrdersForStore(storeId, status, limit, offset);
//    }
//}
