package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.CartItem;
import com.Group11.soulfulplates.models.Order;
import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.repository.CartItemRepository;
import com.Group11.soulfulplates.repository.OrderRepository;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;

        @Autowired
    public OrderServiceImpl(UserRepository userRepository, StoreRepository storeRepository, OrderRepository orderRepository, CartItemRepository cartItemRepository) {
            this.userRepository = userRepository;
            this.storeRepository = storeRepository;
            this.orderRepository = orderRepository;
            this.cartItemRepository = cartItemRepository;
    }


    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        // Find user by orderId
        Optional<User> user = userRepository.findById(request.getUserId());
        // Find store by storeId
        Store store = storeRepository.getReferenceById(request.getUserId());

        Order order = new Order();
        if(user.isPresent()){
            order.setUser(user.get());
        }
        order.setStore(store);
        order.setInstructions(request.getInstructions());
        order.setStatus("Pending");
        order.setRatingId(null);
        order.setUpdatedAt(new Date());
        order.setCreatedAt(new Date());

        System.out.println(order);

        order = orderRepository.save(order);

        // Logic to create and save CartItem entities
        for (CreateOrderRequest.SelectedItem item : request.getSelectedItems()) {
            CartItem cartItem = new CartItem();
            cartItem.setOrder(order);
            cartItem.setItemId(item.getMenuItemId());
            cartItem.setName(item.getItemName());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setPrice(item.getPrice());
            // Save each CartItem
            cartItemRepository.save(cartItem);
        }

        CreateOrderResponse.Data temp = new CreateOrderResponse.Data(order.getOrderId());

        return new CreateOrderResponse(1, temp, "Order Created.");
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        if(!orderRepository.existsById(orderId)){
            return null;
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        order.setStatus(status);
        return orderRepository.save(order);
    }

}
