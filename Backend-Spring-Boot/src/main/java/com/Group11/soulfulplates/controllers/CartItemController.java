package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.response.CartItemDTO;
import com.Group11.soulfulplates.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/cart")
    public class CartItemController {

        @Autowired
        private CartItemService cartItemService;

        @GetMapping("/{userId}")
        public ResponseEntity<List<CartItemDTO>> getCartItemsByUserId(@PathVariable Long userId) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>userId");
            System.out.println(userId);
            List<CartItemDTO> cartItems = cartItemService.getCartItemsByUserId(userId);
            return ResponseEntity.ok(cartItems);
        }
    }