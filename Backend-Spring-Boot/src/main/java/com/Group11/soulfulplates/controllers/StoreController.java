package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.AddressService;
import com.Group11.soulfulplates.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sellers")
public class StoreController {

    private final StoreService sellerService;
    private final AddressService addressService;

    @Autowired
    public StoreController(StoreService sellerService, AddressService addressService) {
        this.sellerService = sellerService;
        this.addressService = addressService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createStore(@RequestBody Store seller) {
        Store newStore = sellerService.createStore(seller);
        if (!sellerService.existsById(seller.getStoreId())){
            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
        };
        return ResponseEntity.ok(new MessageResponse(1, "Store Created Successfully!",newStore));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getStoreById(@PathVariable Long id) {
        return sellerService.getStoreById(id)
                .map(seller -> ResponseEntity.ok(new MessageResponse(1, "Store Found", seller))) // If cart is found, return the cart
                .orElseGet(() -> ResponseEntity.ok(new MessageResponse(1, "No Store Found", null))); // No cart found case
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> sellers = sellerService.getAllStores();
        return ResponseEntity.ok(sellers);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStore(@PathVariable Long id, @RequestBody Store seller) {
        seller.setStoreId(id); // Ensure the seller ID is set to the path variable ID
//        seller.setAddress(addressService.getAddressById(seller.getAddressId()));
        if (!sellerService.existsById(id)){
            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
        };
        Store updatedStore = sellerService.updateStore(seller);
        return ResponseEntity.ok(new MessageResponse(1, "Store Details Updated Successfully!", updatedStore));
    }

    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        if (!sellerService.existsById(id)){
            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
        };
        sellerService.deleteStore(id);
        return ResponseEntity.ok(new MessageResponse(-1, "Store Deleted Successfully!", null));
    }
}
