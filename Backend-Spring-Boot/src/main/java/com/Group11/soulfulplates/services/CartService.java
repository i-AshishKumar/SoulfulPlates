package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartService {

    Optional<Cart> getCartsByUserId(Long userId);
}
