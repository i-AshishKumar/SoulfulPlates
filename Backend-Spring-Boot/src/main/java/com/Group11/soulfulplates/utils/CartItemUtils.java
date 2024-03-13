package com.Group11.soulfulplates.utils;

import com.Group11.soulfulplates.models.CartItem;
import java.util.List;
import java.util.stream.Collectors;

public class CartItemUtils {

    public static List<Long> extractItemIds(List<CartItem> cartItems) {
        return cartItems.stream() // Convert the list to a stream
                .map(CartItem::getItemId) // Transform each CartItem to its itemId
                .collect(Collectors.toList()); // Collect the results into a new list
    }
}
