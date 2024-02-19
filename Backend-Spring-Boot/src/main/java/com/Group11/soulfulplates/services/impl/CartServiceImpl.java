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

//    @Override
//    public Cart createOrUpdateCart(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId)
//                .orElse(new Cart());
//
//        if (cart.getCartId() == null) {
//            cart.setUserId(userId);
//            cart.setCreatedDate(LocalDateTime.now());
//        }
//        cart.setLastUpdatedDate(LocalDateTime.now()); // Update the last updated date
//        return cartRepository.save(cart); // Save the cart and return it
//    }

    @Override
    public boolean existsByCartId(Long id) {
        return cartRepository.existsByCartId(id);
    }

    @Override
    public Cart createOrUpdateCart(Long userId, Long sellerId) {
        // Check if a cart exists for the given userId and sellerId
        Optional<Cart> existingCart = cartRepository.findByUserIdAndServiceProviderId(userId, sellerId);
        if (existingCart.isPresent()) {
            // If cart exists, return the existing one
            return existingCart.get();
        } else {
            // If not, create a new cart
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setServiceProviderId(sellerId);
            newCart.setCreatedDate(LocalDateTime.now());
            newCart.setLastUpdatedDate(LocalDateTime.now());
            return cartRepository.save(newCart);
        }
    }

}
