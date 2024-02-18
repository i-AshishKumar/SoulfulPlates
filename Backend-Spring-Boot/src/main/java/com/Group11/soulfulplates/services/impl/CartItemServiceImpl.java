package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.payload.response.CartItemDTO;
import com.Group11.soulfulplates.repository.CartItemRepository;
import com.Group11.soulfulplates.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDTO> getCartItemsByUserId(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findCartItemsByUserId(userId);
        return cartItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CartItemDTO convertToDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();

        dto.setCartItemId(cartItem.getCartItemId());
        dto.setUserId(cartItem.getUserId());
        dto.setMenuId(cartItem.getMenuId());
        dto.setQuantity(cartItem.getQuantity());
        dto.setNotes(cartItem.getNotes());
        dto.setCreated(cartItem.getCreated());
        dto.setUpdated(cartItem.getUpdated());
        dto.setOrderId(cartItem.getOrderId());
        dto.setIsActive(cartItem.getIsActive());

        return dto;
    }
}
