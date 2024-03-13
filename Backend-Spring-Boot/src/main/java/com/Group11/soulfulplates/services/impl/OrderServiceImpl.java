package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.*;
import com.Group11.soulfulplates.payload.request.CreateOrderRequest;
import com.Group11.soulfulplates.payload.response.CreateOrderResponse;
import com.Group11.soulfulplates.payload.response.OrderDetailsResponse;
import com.Group11.soulfulplates.repository.*;
import com.Group11.soulfulplates.services.OrderService;
import com.Group11.soulfulplates.utils.CartItemUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    @Autowired
    private final MenuItemRepository menuItemRepository; // Assume this exists

        @Autowired
    public OrderServiceImpl(UserRepository userRepository, StoreRepository storeRepository, OrderRepository orderRepository, CartItemRepository cartItemRepository, MenuItemRepository menuItemRepository) {
            this.userRepository = userRepository;
            this.storeRepository = storeRepository;
            this.orderRepository = orderRepository;
            this.cartItemRepository = cartItemRepository;
            this.menuItemRepository = menuItemRepository;
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
        order.setRating(null);
        order.setUpdatedAt(new Date());
        order.setCreatedAt(new Date());

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

    @Override
    public OrderDetailsResponse getOrderDetails(Long userId, Long orderId) throws Exception{
        // Find the order by id and check if it belongs to the user
        Order order = orderRepository.findByOrderIdAndUserId(orderId, userId)
                .orElseThrow(() -> new RuntimeException("Order not found or does not belong to the user"));



        // Convert the order entity to OrderDetailsData
        OrderDetailsResponse.OrderDetails data = mapOrderToOrderDetailsData(order);

        // Construct the response object
        return new OrderDetailsResponse(1, "Success", data);
    }

    private OrderDetailsResponse.OrderDetails mapOrderToOrderDetailsData(Order order) throws Exception{
        OrderDetailsResponse.OrderDetails orderDetails = new OrderDetailsResponse.OrderDetails();

        orderDetails.setOrderId(order.getOrderId());
        orderDetails.setOrderStatus(order.getStatus());
        orderDetails.setCreatedDate(order.getCreatedAt());
//                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        orderDetails.setUserId(order.getUser().getId());
        orderDetails.setStoreId(order.getStore().getStoreId());
        // Assuming that you have methods to get the rating and feedback
        if(order.getRating()!= null){
            orderDetails.setRating(order.getRating().getRating());
            orderDetails.setFeedback(order.getRating().getFeedback());
        } else {
            orderDetails.setRating(null);
            orderDetails.setFeedback(null);
        }
        // Assuming that you have a method to get the payment status
        orderDetails.setPaymentStatus(order.getStatus());

        List<CartItem> cartItems = cartItemRepository.findByOrderOrderId(order.getOrderId());
        List<Long> itemIds = null;
        if(cartItems.size() > 0){
            itemIds = CartItemUtils.extractItemIds(cartItems);
        }

        if(itemIds == null){
            throw new Exception("Menu Items not found");
        }

        List<MenuItem> menuItems = menuItemRepository.findAllById(itemIds);

        orderDetails.setItems(menuItems.stream()
                .map(this::mapMenuItemToDTO)
                .collect(Collectors.toList()));

        return orderDetails;
    }

    private OrderDetailsResponse.OrderDetails.MenuItemDTO mapMenuItemToDTO(MenuItem menuItem) {
        OrderDetailsResponse.OrderDetails.MenuItemDTO menuItemDTO = new OrderDetailsResponse.OrderDetails.MenuItemDTO();
        menuItemDTO.setItemId(menuItem.getMenuItemId());
        menuItemDTO.setStoreId(menuItem.getStoreId());
        menuItemDTO.setItemName(menuItem.getItemName());
        menuItemDTO.setItemImage(menuItem.getItemImage());
        menuItemDTO.setItemPrice(menuItem.getItemPrice());
        menuItemDTO.setType(menuItem.getType());
        menuItemDTO.setCategoryId(menuItem.getCategoryId());
//        menuItemDTO.setCategory(menuItem.getCategory().getName());
        menuItemDTO.setSubCategoryId(menuItem.getSubCategoryId());
//        menuItemDTO.setSubCategory(menuItem.getSubCategory().getName());
        menuItemDTO.setServingType(menuItem.getServingType());
        menuItemDTO.setPortion(menuItem.getPortion());
        menuItemDTO.setInStock(menuItem.getInStock());
        menuItemDTO.setIsRecommended(menuItem.getIsRecommended());
        menuItemDTO.setDescription(menuItem.getDescription());

        return menuItemDTO;
    }

}
