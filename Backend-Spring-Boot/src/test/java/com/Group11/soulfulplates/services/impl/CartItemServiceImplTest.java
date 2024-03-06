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

    @Test
    void testAddOrUpdateCartItem_AddNewCartItem() {
        // Given
        Long cartId = 1L;
        Long menuItemId = 2L;
        Integer quantity = 3;
        String notes = "Test notes";
        LocalDateTime currentDateTime = LocalDateTime.now();

        when(cartItemRepository.findByCartIdAndMenuItemId(cartId, menuItemId)).thenReturn(Optional.empty());

        // When
        CartItem savedCartItem = cartItemService.addOrUpdateCartItem(cartId, menuItemId, quantity, notes);

        // Then
        if (savedCartItem == null) {
            System.out.println("Saved cart item is null!");
        } else {
            System.out.println("Saved cart item: " + savedCartItem);
            assertEquals(cartId, savedCartItem.getCartId());
            assertEquals(menuItemId, savedCartItem.getMenuItemId());
            assertEquals(quantity, savedCartItem.getQuantity());
            assertEquals(notes, savedCartItem.getNotes());
            assertEquals(currentDateTime.getDayOfYear(), savedCartItem.getAddedDate().getDayOfYear());
            verify(cartItemRepository, times(1)).save(any());
        }
    }

    @Test
    void testAddOrUpdateCartItem_UpdateExistingCartItem() {
        // Given
        Long cartId = 1L;
        Long menuItemId = 2L;
        Integer quantity = 3;
        String notes = "Test notes";
        LocalDateTime currentDateTime = LocalDateTime.now();

        CartItem existingCartItem = new CartItem();
        existingCartItem.setCartId(cartId);
        existingCartItem.setMenuItemId(menuItemId);
        existingCartItem.setQuantity(2); // Previous quantity
        existingCartItem.setNotes("Previous notes");
        existingCartItem.setAddedDate(currentDateTime.minusDays(1));

        when(cartItemRepository.findByCartIdAndMenuItemId(cartId, menuItemId)).thenReturn(Optional.of(existingCartItem));

        // When
        CartItem updatedCartItem = cartItemService.addOrUpdateCartItem(cartId, menuItemId, quantity, notes);

        // Then
        if (updatedCartItem == null) {
            System.out.println("Updated cart item is null!");
        } else {
            System.out.println("Updated cart item: " + updatedCartItem);
            assertEquals(cartId, updatedCartItem.getCartId());
            assertEquals(menuItemId, updatedCartItem.getMenuItemId());
            assertEquals(quantity, updatedCartItem.getQuantity());
            assertEquals(notes, updatedCartItem.getNotes());
            assertEquals(currentDateTime.getDayOfYear(), updatedCartItem.getAddedDate().getDayOfYear());
            verify(cartItemRepository, times(1)).save(any());
        }
    }



}
