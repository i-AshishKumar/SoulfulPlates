package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.AddressService;
import com.Group11.soulfulplates.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/updateStore/{userId}")
    public ResponseEntity<?> updateStore(@PathVariable Long userId, @RequestBody(required = false) Store storeDetails) {
        try {
            Store updatedStore = storeService.updateStoreByUserId(userId, storeDetails);
            return ResponseEntity.ok(new MessageResponse(1, "Store Details Updated", null));
        } catch (Exception e) {
            // map to hold response body
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", -1);
            responseBody.put("description", "Seller's Store not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }
    }
}

