package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Cart;
import com.Group11.soulfulplates.repository.CartRepository;
import com.Group11.soulfulplates.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

}
