package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Seller;
import com.Group11.soulfulplates.models.SellerAddress;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.payload.response.SellerResponse;
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

    private final SellerService sellerService;
    private final AddressService addressService;

    @Autowired
    public SellerController(SellerService sellerService, AddressService addressService) {
        this.sellerService = sellerService;
        this.addressService = addressService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createSeller(@RequestBody Seller seller) {
        Seller newSeller = sellerService.createSeller(seller);
        if (!sellerService.existsById(seller.getSellerId())){
            return ResponseEntity.ok(new MessageResponse(-1, "Seller Not Found!", null));
        };
        return ResponseEntity.ok(new MessageResponse(1, "Seller Created Successfully!",newSeller));
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> getSellerById(@PathVariable Long id) {
//        return sellerService.getSellerById(id)
//                .map(seller -> ResponseEntity.ok(new MessageResponse(1, "Seller Found", seller))) // If cart is found, return the cart
//                .orElseGet(() -> ResponseEntity.ok(new MessageResponse(1, "No Seller Found", null))); // No cart found case
//    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getSellerById(@PathVariable Long id) {
        Optional<Seller> sellerOpt = sellerService.getSellerById(id);
        if (!sellerOpt.isPresent()) {
            return ResponseEntity.ok(new SellerResponse(-1, null, "Seller not found"));
        }

        Seller seller = sellerOpt.get();
        SellerResponse.SellerData sellerData = new SellerResponse.SellerData();
        sellerData.setId(seller.getSellerId());
        // Assuming token, username (if applicable), firstname, and image are attributes of Seller or related entities
        sellerData.setToken("some-token");
        sellerData.setUsername(seller.getName()); // Placeholder: Adjust according to actual attribute
        sellerData.setEmail(seller.getEmail());
        sellerData.setContactNumber(seller.getContactNumber());
        sellerData.setFirstname("YourFirstname"); // Placeholder
        sellerData.setImage("YourImageURL"); // Placeholder
//        SellerResponse.SellerData sellerData = new SellerResponse.SellerData();
        SellerResponse.SellerData.SellerDetails sellerDetails = new SellerResponse.SellerData.SellerDetails();
        sellerDetails.setSellerId(seller.getSellerId());
        sellerDetails.setStoreImage("YourStoreImageURL"); // Placeholder
        sellerDetails.setStoreName("YourStoreName"); // Placeholder
        sellerDetails.setStoreContactNumber(seller.getContactNumber()); // Assuming this is the same as seller's contact number
        sellerDetails.setStoreEmail(seller.getEmail());

        SellerResponse.SellerData.SellerDetails.StoreAddress storeAddress = new SellerResponse.SellerData.SellerDetails.StoreAddress();
        SellerAddress address = seller.getSellerAddress(); // Placeholder for actual retrieval method
        storeAddress.setLocationId(address.getLocationId());
        storeAddress.setLocationName(address.getLocationName());
        storeAddress.setAddress(address.getAddress());
        storeAddress.setLat(address.getLat());
        storeAddress.setLon(address.getLon());
        storeAddress.setPostalCode(address.getPostalCode());
        storeAddress.setProvince(address.getProvince());
        storeAddress.setCity(address.getCity());

        sellerDetails.setStoreAddress(storeAddress);
        sellerData.setSeller(sellerDetails);

        SellerResponse response = new SellerResponse(1, sellerData, "Success");
        return ResponseEntity.ok(response);
    }



    @GetMapping
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        seller.setSellerId(id); // Ensure the seller ID is set to the path variable ID
//        seller.setAddress(addressService.getAddressById(seller.getAddressId()));
        if (!sellerService.existsById(id)){
            return ResponseEntity.ok(new MessageResponse(-1, "Seller Not Found!", null));
        };
        Seller updatedSeller = sellerService.updateSeller(seller);
        return ResponseEntity.ok(new MessageResponse(1, "Seller Details Updated Successfully!", updatedSeller));
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
