package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.payload.response.CartItemDTO;

import java.util.List;

public interface CartItemService {
    List<CartItemDTO> getCartItemsByUserId(Long userId);
}
