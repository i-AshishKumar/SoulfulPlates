package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    private CartServiceImpl cartService;

    private CartItemServiceImpl cartItemService;

    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartServiceImpl(cartRepository,cartItemService);
    }

    @Test
    void testgetCartsByUserId_ReturnsCarts() {
        // Given
        Long userId = 1L;
        List<Cart> expectedCarts = new ArrayList<>();
        expectedCarts.add(new Cart());
        expectedCarts.add(new Cart());

        when(cartRepository.findAllCartsOfUserId(userId)).thenReturn(Optional.of(expectedCarts));

        // When
        Optional<List<Cart>> result = cartService.getCartsByUserId(userId);

        // Then
        assertEquals(Optional.of(expectedCarts), result);
        verify(cartRepository, times(1)).findAllCartsOfUserId(userId);
    }
}
