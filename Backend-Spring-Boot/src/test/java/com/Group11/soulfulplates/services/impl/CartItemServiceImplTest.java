package com.Group11.soulfulplates.services.impl;


import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CartItemServiceImplTest {

    private CartItemServiceImpl cartItemService;

    @Mock
    private CartItemRepository cartItemRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
        cartItemService = new CartItemServiceImpl(cartItemRepository);
    }

    @Test
    void addCartItemTest() {
        // Given
        Long cartId = 1L;
        Long menuItemId = 2L;
        Integer quantity = 3;
        String notes = "Test notes";

        CartItem cartItemToSave = new CartItem();
        cartItemToSave.setCartId(cartId);
        cartItemToSave.setMenuItemId(menuItemId);
        cartItemToSave.setQuantity(quantity);
        cartItemToSave.setAddedDate(LocalDateTime.now());
        cartItemToSave.setNotes(notes);

        when(cartItemRepository.save(any())).thenReturn(cartItemToSave);

        // When
        CartItem savedCartItem = cartItemService.addCartItem(cartId, menuItemId, quantity, notes);

        // Then
        assertEquals(cartId, savedCartItem.getCartId());
        assertEquals(menuItemId, savedCartItem.getMenuItemId());
        assertEquals(quantity, savedCartItem.getQuantity());
        assertEquals(notes, savedCartItem.getNotes());
        verify(cartItemRepository, times(1)).save(any());
    }



}
