package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
    Order updateOrderStatus(Long orderId, String status);
    OrderDetailsResponse getOrderDetails(Long userId, Long orderId) throws Exception;
}
