package com.Group11.soulfulplates.controllers;
import com.Group11.soulfulplates.models.Address;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.AddressRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PutMapping("/toggle-notification/{userId}")
    public ResponseEntity<MessageResponse> toggleNotificationFlag(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Toggle the value of notificationFlag
        user.setNotificationFlag(!user.isNotificationFlag());

        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(1, "Notification flag toggled successfully!", null));
    }

    @PostMapping("/addresses/{userId}")
    public ResponseEntity<MessageResponse> createAddressForUser(@PathVariable Long userId, @RequestBody Address address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        address.setUser(user);
        addressRepository.save(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address saved successfully!", null));
    }


    @GetMapping("/addresses/{userId}")
    public ResponseEntity<MessageResponse> getAllAddressesForUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Retrieve all addresses associated with the given user ID
        List<Address> addresses = addressRepository.findByUser(user);

        // Remove the user information from each address object
        addresses.forEach(address -> address.setUser(null));

        // Create a response message with the addresses list directly under the 'data' field
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode responseData = objectMapper.createArrayNode();
        for (Address address : addresses) {
            // Convert the address to a JSON node
            ObjectNode addressNode = objectMapper.convertValue(address, ObjectNode.class);
            // Remove the "user" field from the address node
            addressNode.remove("user");
            // Add the address node to the response data
            responseData.add(addressNode);
        }

        // Create a response message with the modified addresses list
        MessageResponse response = new MessageResponse(1, "Addresses fetched successfully!", responseData);

        return ResponseEntity.ok(response);
    }



    // Update an existing address for a user
    @PutMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<MessageResponse> updateAddressForUser(@PathVariable Long userId, @PathVariable Long addressId, @RequestBody Address addressDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setPostalCode(addressDetails.getPostalCode());
        address.setCountry(addressDetails.getCountry());
        address.setLatitude(addressDetails.getLatitude());
        address.setLongitude(addressDetails.getLongitude());
        address.setLabel(addressDetails.getLabel());

        addressRepository.save(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address updated successfully!", null));
    }

    // Delete an address for a user
    @DeleteMapping("/addresses/{userId}/{addressId}")
    public ResponseEntity<MessageResponse> deleteAddressForUser(@PathVariable Long userId, @PathVariable Long addressId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        addressRepository.delete(address);

        return ResponseEntity.ok(new MessageResponse(1, "Address deleted successfully!", null));
    }
}
