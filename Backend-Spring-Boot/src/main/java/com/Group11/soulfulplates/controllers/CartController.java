package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.Group11.soulfulplates.services.SellerService;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final SellerService sellerService;

    @Autowired
    public CartController(CartService cartService, SellerService sellerService) {
        this.cartService = cartService;
        this.sellerService = sellerService; // Initialize via constructor
    }

    @GetMapping("/getCartsByUserId")
    public ResponseEntity<?> getCartsByUserId(@RequestParam Long userId) {
        // Validate userId parameter
        if (userId == null || !userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(new ResponseObject(-1, "Invalid userId.", Collections.emptyList()));
        }

        // Attempt to fetch the cart for the given userId
        return cartService.getCartsByUserId(userId)
                .map(cart -> ResponseEntity.ok().body(cart)) // If cart is found, return the cart
                .orElseGet(() -> ResponseEntity.ok().body(null)); // No cart found case
    }

    static class ResponseObject {
        private int code;
        private String description;
        private Object data;

        public ResponseObject(int code, String description, Object data) {
            this.code = code;
            this.description = description;
            this.data = data;
        }

        // Getters and Setters
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
