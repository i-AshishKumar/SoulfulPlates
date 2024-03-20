package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.OrdersResponse;
import com.Group11.soulfulplates.repository.CartItemRepository;
import com.Group11.soulfulplates.repository.OrderRepository;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder_SuccessfullyCreated() {
        // Given
        Long userId = 1L;
        Long storeId = 2L;
        String instructions = "Some instructions";

        CreateOrderRequest request = new CreateOrderRequest();
        request.setUserId(userId);
        request.setStoreId(storeId);
        request.setInstructions(instructions);

        User user = new User();
        user.setId(userId);
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findById(userId)).thenReturn(optionalUser);

        Store store = new Store();
        store.setStoreId(storeId);
        when(storeRepository.getReferenceById(storeId)).thenReturn(store);

        Order savedOrder = new Order();
        savedOrder.setOrderId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        CreateOrderRequest.SelectedItem item = new CreateOrderRequest.SelectedItem();
        item.setMenuItemId(1L);
        item.setItemName("Item");
        item.setQuantity(2);
        item.setPrice(10.0);

        request.setSelectedItems(Collections.singletonList(item));

        // When
        CreateOrderResponse response = orderService.createOrder(request);

        // Then
        assertEquals(1, response.getCode());
        assertNotNull(response.getData());
        assertEquals("Order Created.", response.getDescription());
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    void testUpdateOrderStatus_OrderNotFound_ReturnsNull() {
        // Given
        Long orderId = 1L;
        String status = "Pending";

        when(orderRepository.existsById(orderId)).thenReturn(false);

        // When
        Order result = orderService.updateOrderStatus(orderId, status);

        // Then
        assertNull(result);
        verify(orderRepository, times(1)).existsById(orderId);
        verify(orderRepository, never()).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void testUpdateOrderStatus_OrderFound_SuccessfullyUpdated() {
        // Given
        Long orderId = 1L;
        String status = "Cancelled";

        Order order = new Order();
        order.setOrderId(orderId);

        when(orderRepository.existsById(orderId)).thenReturn(true);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // When
        Order result = orderService.updateOrderStatus(orderId, status);

        // Then
        assertNotNull(result);
        assertEquals(orderId, result.getOrderId());
        assertEquals(status, result.getStatus());
        verify(orderRepository, times(1)).existsById(orderId);
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrderDetails_OrderNotFound_ThrowsException() {
        // Given
        Long userId = 1L;
        Long orderId = 1L;

        when(orderRepository.findByOrderIdAndUserId(orderId, userId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(Exception.class, () -> orderService.getOrderDetails(userId, orderId));
        verify(orderRepository, times(1)).findByOrderIdAndUserId(orderId, userId);
    }

    @Test
    void testGetOrdersForStore_SuccessfulRetrieval() throws Exception {
        // Given
        Long storeId = 1L;
        String status = "Completed";
        Integer limit = 10;
        Integer offset = 0;

        Order order = new Order();
        order.setOrderId(1L);
        Page<Order> ordersPage = new PageImpl<>(Collections.singletonList(order));
        when(orderRepository.findByStoreStoreIdAndStatusOrderByCreatedAtDesc(storeId, status, PageRequest.of(offset, limit)))
                .thenReturn(ordersPage);

        // When
        OrdersResponse response = orderService.getOrdersForStore(storeId, status, limit, offset);

        // Then
        assertEquals(1, response.getCode());
        assertEquals("Success", response.getDescription());
        assertEquals(1, response.getData().size());
        verify(orderRepository, times(1)).findByStoreStoreIdAndStatusOrderByCreatedAtDesc(storeId, status, PageRequest.of(offset, limit));
    }

    @Test
    void testGetOrdersForStore_NoOrders_ReturnsEmptyList() throws Exception {
        // Given
        Long storeId = 1L;
        String status = "Completed";
        Integer limit = 10;
        Integer offset = 0;

        when(orderRepository.findByStoreStoreIdAndStatusOrderByCreatedAtDesc(storeId, status, PageRequest.of(offset, limit)))
                .thenReturn(Page.empty());

        // When
        OrdersResponse response = orderService.getOrdersForStore(storeId, status, limit, offset);

        // Then
        assertEquals(1, response.getCode());
        assertEquals("Success", response.getDescription());
        assertTrue(response.getData().isEmpty());
        verify(orderRepository, times(1)).findByStoreStoreIdAndStatusOrderByCreatedAtDesc(storeId, status, PageRequest.of(offset, limit));
    }

    @Test
    void testGetOrdersForUser_SuccessfulRetrieval() throws Exception {
        // Given
        Long userId = 1L;
        String status = "Completed";
        Integer limit = 10;
        Integer offset = 0;

        Order order = new Order();
        order.setOrderId(1L);
        when(orderRepository.findByUserIdAndStatusOrderByCreatedAtDesc(userId, status, PageRequest.of(offset, limit)))
                .thenReturn(new PageImpl<>(Collections.singletonList(order)));

        // When
        OrdersResponse response = orderService.getOrdersForUser(userId, status, limit, offset);

        // Then
        assertEquals(1, response.getCode());
        assertEquals("Success", response.getDescription());
        assertEquals(1, response.getData().size());
        verify(orderRepository, times(1)).findByUserIdAndStatusOrderByCreatedAtDesc(userId, status, PageRequest.of(offset, limit));
    }

    @Test
    void testGetOrdersForUser_NoOrders_ReturnsEmptyList() throws Exception {
        // Given
        Long userId = 1L;
        String status = "Completed";
        Integer limit = 10;
        Integer offset = 0;

        when(orderRepository.findByUserIdAndStatusOrderByCreatedAtDesc(userId, status, PageRequest.of(offset, limit)))
                .thenReturn(Page.empty());

        // When
        OrdersResponse response = orderService.getOrdersForUser(userId, status, limit, offset);

        // Then
        assertEquals(1, response.getCode());
        assertEquals("Success", response.getDescription());
        assertTrue(response.getData().isEmpty());
        verify(orderRepository, times(1)).findByUserIdAndStatusOrderByCreatedAtDesc(userId, status, PageRequest.of(offset, limit));
    }

}
