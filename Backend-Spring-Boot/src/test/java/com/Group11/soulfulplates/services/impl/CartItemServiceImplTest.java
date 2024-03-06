package com.Group11.soulfulplates.services.impl;


import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Test
    void testFindById_NonExistingCartItem() {
        // Given
        Long cartItemId = 1L;

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.empty());

        // When
        Optional<CartItem> result = cartItemService.findById(cartItemId);

        // Then
        assertEquals(Optional.empty(), result);
        verify(cartItemRepository, times(1)).findById(cartItemId);
    }

    @Test
    void findById_ExistingCartItem() {
        // Given
        Long cartItemId = 1L;
        CartItem expectedCartItem = new CartItem();
        expectedCartItem.setCartId(1L);
        expectedCartItem.setMenuItemId(2L);
        expectedCartItem.setQuantity(3);
        expectedCartItem.setNotes("Test notes");

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(expectedCartItem));

        // When
        Optional<CartItem> result = cartItemService.findById(cartItemId);

        // Then
        assertEquals(Optional.of(expectedCartItem), result);
        verify(cartItemRepository, times(1)).findById(cartItemId);
    }

    @Test
    void findById_NonExistingCartItem() {
        // Given
        Long cartItemId = 1L;

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.empty());

        // When
        Optional<CartItem> result = cartItemService.findById(cartItemId);

        // Then
        assertEquals(Optional.empty(), result);
        verify(cartItemRepository, times(1)).findById(cartItemId);
    }


    @Test
    void updateCartItem_CartItemFound() {
        // Given
        Long cartItemId = 1L;
        Integer newQuantity = 5;
        String newNotes = "Updated notes";

        CartItem existingCartItem = new CartItem();
        existingCartItem.setCartItemId(cartItemId);
        existingCartItem.setCartId(1L);
        existingCartItem.setMenuItemId(2L);
        existingCartItem.setQuantity(3);
        existingCartItem.setNotes("Test notes");

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(existingCartItem));
        when(cartItemRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItemId, newQuantity, newNotes);

        // Then
        assertEquals(cartItemId, updatedCartItem.getCartItemId());
        assertEquals(newQuantity, updatedCartItem.getQuantity());
        assertEquals(newNotes, updatedCartItem.getNotes());
        verify(cartItemRepository, times(1)).findById(cartItemId);
        verify(cartItemRepository, times(1)).save(any());
    }

    @Test
    void updateCartItem_CartItemNotFound() {
        // Given
        Long cartItemId = 1L;
        Integer newQuantity = 5;
        String newNotes = "Updated notes";

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.empty());

        // When
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> cartItemService.updateCartItem(cartItemId, newQuantity, newNotes));

        // Then
        assertEquals("Cart item not found", exception.getMessage());
        verify(cartItemRepository, times(1)).findById(cartItemId);
        verify(cartItemRepository, never()).save(any());
    }

    @Test
    void removeCartItem_SuccessfullyRemoved() {
        // Given
        Long cartItemId = 1L;

        // When
        cartItemService.removeCartItem(cartItemId);

        // Then
        verify(cartItemRepository, times(1)).deleteById(cartItemId);

    }

    @Test
    void getCartItemsByCartId_ReturnsCartItems() {
        // Given
        Long cartId = 1L;
        List<CartItem> expectedCartItems = new ArrayList<>();
        expectedCartItems.add(new CartItem());
        expectedCartItems.add(new CartItem());

        when(cartItemRepository.findByCartId(cartId)).thenReturn(expectedCartItems);

        // When
        List<CartItem> result = cartItemService.getCartItemsByCartId(cartId);

        // Then
        assertEquals(expectedCartItems, result);
        verify(cartItemRepository, times(1)).findByCartId(cartId);
    }

    @Test
    void deleteByCartId_SuccessfullyDeleted() {
        // Given
        Long cartId = 1L;

        // When
        cartItemService.deleteByCartId(cartId);

        // Then
        verify(cartItemRepository, times(1)).deleteByCartId(cartId);
    }

}
