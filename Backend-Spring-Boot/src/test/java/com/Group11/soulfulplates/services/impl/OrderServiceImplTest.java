package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.*;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.repository.CartItemRepository;
import com.Group11.soulfulplates.repository.OrderRepository;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private OrderRepository orderRepository;

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

}
