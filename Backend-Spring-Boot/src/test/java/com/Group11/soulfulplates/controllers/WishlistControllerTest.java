package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Wishlist;
import com.Group11.soulfulplates.payload.request.WishlistRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.WishlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WishlistControllerTest {

    @Mock
    private WishlistServiceImpl wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllWishlists() {
        List<Wishlist> wishlistList = new ArrayList<>();
        when(wishlistService.getAllWishlists()).thenReturn(wishlistList);

        List<Wishlist> result = wishlistController.getAllWishlists();

        assertEquals(wishlistList, result);
        verify(wishlistService, times(1)).getAllWishlists();
    }

    @Test
    void testGetWishlistById() {
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(1L);
        Optional<Wishlist> optionalWishlist = Optional.of(wishlist);
        when(wishlistService.getWishlistById(1L)).thenReturn(optionalWishlist);

        ResponseEntity<MessageResponse> response = wishlistController.getWishlistById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getCode());
        assertEquals("Wishlist found", response.getBody().getDescription());
        assertEquals(wishlist, response.getBody().getData());
        verify(wishlistService, times(1)).getWishlistById(1L);
    }

    @Test
    void testGetWishlistByIdNotFound() {
        Optional<Wishlist> optionalWishlist = Optional.empty();
        when(wishlistService.getWishlistById(1L)).thenReturn(optionalWishlist);

        ResponseEntity<MessageResponse> response = wishlistController.getWishlistById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(-1, response.getBody().getCode());
        assertEquals("Wishlist not found", response.getBody().getDescription());
        assertEquals(null, response.getBody().getData());
        verify(wishlistService, times(1)).getWishlistById(1L);
    }

    @Test
    void testCreateWishlist() {
        WishlistRequest wishlistRequest = new WishlistRequest();
        Wishlist wishlist = new Wishlist();
        wishlist.setWishlistId(1L);
        when(wishlistService.saveOrUpdateWishlist(wishlistRequest)).thenReturn(wishlist);

        ResponseEntity<MessageResponse> response = wishlistController.createWishlist(wishlistRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getCode());
        assertEquals("Wishlist created", response.getBody().getDescription());
        assertEquals(wishlist, response.getBody().getData());
        verify(wishlistService, times(1)).saveOrUpdateWishlist(wishlistRequest);
    }

    @Test
    void testDeleteWishlist() {
        when(wishlistService.deleteWishlist(1L)).thenReturn(true);

        ResponseEntity<MessageResponse> response = wishlistController.deleteWishlist(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getCode());
        assertEquals("Wishlist deleted", response.getBody().getDescription());
        assertEquals(null, response.getBody().getData());
        verify(wishlistService, times(1)).deleteWishlist(1L);
    }
}
