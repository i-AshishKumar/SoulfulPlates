package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceImplTest {


    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartItemServiceImpl cartItemService;

    @Mock
    private CartRepository cartRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartService = new CartServiceImpl(cartRepository,cartItemService);
    }

    @Test
    void testGetCartsByUserId_ReturnsCarts() {
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

    @Test
    void testExistsByCartId_ReturnsTrue() {
        // Given
        Long cartId = 1L;

        when(cartRepository.existsByCartId(cartId)).thenReturn(true);

        // When
        boolean result = cartService.existsByCartId(cartId);

        // Then
        assertEquals(true, result);
        verify(cartRepository, times(1)).existsByCartId(cartId);
    }

    @Test
    void testExistsByCartId_ReturnsFalse() {
        // Given
        Long cartId = 1L;

        when(cartRepository.existsByCartId(cartId)).thenReturn(false);

        // When
        boolean result = cartService.existsByCartId(cartId);

        // Then
        assertEquals(false, result);
        verify(cartRepository, times(1)).existsByCartId(cartId);
    }

    @Test
    void testCreateCart_SuccessfullyCreated() {
        // Given
        Long userId = 1L;
        Long sellerId = 2L;
        LocalDateTime now = LocalDateTime.now();

        Cart mockedCart = new Cart();
        mockedCart.setUserId(userId);
        mockedCart.setSellerId(sellerId);
        mockedCart.setCreatedDate(now);
        mockedCart.setLastUpdatedDate(now);

        // When
        when(cartRepository.save(any())).thenReturn(mockedCart);
        Cart createdCart = cartService.createCart(userId, sellerId);

        // Then
        assertEquals(userId, createdCart.getUserId());
        assertEquals(sellerId, createdCart.getSellerId());
        assertEquals(now.getDayOfYear(), createdCart.getCreatedDate().getDayOfYear());
        assertEquals(now.getDayOfYear(), createdCart.getLastUpdatedDate().getDayOfYear());
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    void testUpdateCart_SuccessfullyUpdated() {
        // Given
        Long userId = 1L;
        Long sellerId = 2L;
        LocalDateTime now = LocalDateTime.now();

        // When
        cartService.updateCart(userId, sellerId);

        // Then
        verify(cartRepository, times(1)).updateCart(eq(userId), eq(sellerId), any(LocalDateTime.class));
    }

    @Test
    void testDeleteCartAndItems_SuccessfullyDeleted() {
        // Given
        Long cartId = 1L;

        // When
        cartService.deleteCartAndItems(cartId);

        // Then
        verify(cartItemService, times(1)).deleteByCartId(cartId);
        verify(cartRepository, times(1)).deleteById(cartId);
    }

    @Test
    void testGetOrCreateCart_ReturnsExistingCart() {
        // Given
        Long userId = 1L;
        Long sellerId = 2L;
        Cart existingCart = new Cart();
        existingCart.setUserId(userId);
        existingCart.setSellerId(sellerId);

        when(cartRepository.findByUserIdAndSellerId(userId, sellerId)).thenReturn(Optional.of(existingCart));

        // When
        Cart result = cartService.getOrCreateCart(userId, sellerId);

        // Then
        assertEquals(existingCart, result);
        verify(cartRepository, times(1)).findByUserIdAndSellerId(userId, sellerId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testGetOrCreateCart_CreatesNewCart() {
        // Given
        Long userId = 1L;
        Long sellerId = 2L;
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setSellerId(sellerId);

        when(cartRepository.findByUserIdAndSellerId(userId, sellerId)).thenReturn(Optional.empty());
        when(cartRepository.save(any(Cart.class))).thenReturn(newCart);

        // When
        Cart result = cartService.getOrCreateCart(userId, sellerId);

        // Then
        assertEquals(userId, result.getUserId());
        assertEquals(sellerId, result.getSellerId());
        verify(cartRepository, times(1)).findByUserIdAndSellerId(userId, sellerId);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }


}
