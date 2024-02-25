package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Seller;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.AddressService;
import com.Group11.soulfulplates.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        Seller newSeller = sellerService.createSeller(seller);
        if (!sellerService.existsById(seller.getSellerId())){
            return ResponseEntity.ok(new MessageResponse(-1, "Seller Not Found!", null));
        };
        return ResponseEntity.ok(new MessageResponse(1, "Seller Created Successfully!",newSeller));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getSellerById(@PathVariable Long id) {
        return sellerService.getSellerById(id)
                .map(seller -> ResponseEntity.ok(new MessageResponse(1, "Seller Found", seller))) // If cart is found, return the cart
                .orElseGet(() -> ResponseEntity.ok(new MessageResponse(1, "No Seller Found", null))); // No cart found case
    }
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeller(@PathVariable Long id) {
        if (!sellerService.existsById(id)){
            return ResponseEntity.ok(new MessageResponse(-1, "Seller Not Found!", null));
        };
        sellerService.deleteSeller(id);
        return ResponseEntity.ok(new MessageResponse(-1, "Seller Deleted Successfully!", null));
    }
}
