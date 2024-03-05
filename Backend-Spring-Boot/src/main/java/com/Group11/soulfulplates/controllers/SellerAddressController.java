package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.SellerAddress;
import com.Group11.soulfulplates.services.SellerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller-addresses")
public class SellerAddressController {

    private final SellerAddressService sellerAddressService;

    @Autowired
    public SellerAddressController(SellerAddressService sellerAddressService) {
        this.sellerAddressService = sellerAddressService;
    }

    @PostMapping
    public ResponseEntity<SellerAddress> createSellerAddress(@RequestBody SellerAddress sellerAddress) {
        return ResponseEntity.ok(sellerAddressService.createSellerAddress(sellerAddress));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerAddress> getSellerAddressById(@PathVariable Long id) {
        return sellerAddressService.getSellerAddressById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SellerAddress>> getAllSellerAddresses() {
        return ResponseEntity.ok(sellerAddressService.getAllSellerAddresses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerAddress> updateSellerAddress(@PathVariable Long id, @RequestBody SellerAddress sellerAddress) {
        if (!sellerAddressService.getSellerAddressById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sellerAddress.setLocationId(id);
        return ResponseEntity.ok(sellerAddressService.updateSellerAddress(sellerAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSellerAddress(@PathVariable Long id) {
        if (!sellerAddressService.getSellerAddressById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sellerAddressService.deleteSellerAddress(id);
        return ResponseEntity.ok().build();
    }
}
