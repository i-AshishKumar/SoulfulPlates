package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;



    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createStore(@RequestBody Store seller) {
        Store newStore = storeService.createStore(seller);
        if (!storeService.existsById(seller.getStoreId())){
            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
        };
        return ResponseEntity.ok(new MessageResponse(1, "Store Created Successfully!",newStore));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id)
                .map(seller -> ResponseEntity.ok(new MessageResponse(1, "Store Found", seller))) // If cart is found, return the cart
                .orElseGet(() -> ResponseEntity.ok(new MessageResponse(1, "No Store Found", null))); // No cart found case
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> sellers = storeService.getAllStores();
        return ResponseEntity.ok(sellers);
    }

//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
//    public ResponseEntity<?> updateStore(@PathVariable Long id, @RequestBody Store seller) {
//        seller.setStoreId(id); // Ensure the seller ID is set to the path variable ID
////        seller.setAddress(addressService.getAddressById(seller.getAddressId()));
//        if (!storeService.existsById(id)){
//            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
//        };
//        Store updatedStore = storeService.updateStore(seller);
//        return ResponseEntity.ok(new MessageResponse(1, "Store Details Updated Successfully!", updatedStore));
//    }

    @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        if (!storeService.existsById(id)) {
            return ResponseEntity.ok(new MessageResponse(-1, "Store Not Found!", null));
        }
        ;
        storeService.deleteStore(id);
        return ResponseEntity.ok(new MessageResponse(-1, "Store Deleted Successfully!", null));
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

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/image/{storeId}")
    public ResponseEntity<MessageResponse> updateUserImage(@PathVariable Long storeId,
                                                           @RequestParam("file") MultipartFile file) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + storeId));


        // Check if the uploaded file is not empty
        if (file.isEmpty()) {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to store empty file.", null));
        }

        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String fileNameWithoutExtension = StringUtils.stripFilenameExtension(originalFilename);
        String fileName = StringUtils.cleanPath(fileNameWithoutExtension + "_" + System.currentTimeMillis() + "." + fileExtension);

        try {

            Path uploadsDir = Paths.get(uploadPath);

            if (!Files.exists(uploadsDir)) {
                Files.createDirectories(uploadsDir);
            }
            Path filePath = uploadsDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            // Update the user's profile image URL
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
            store.setStoreImageUrl(fileUrl);

            storeRepository.save(store);

            return ResponseEntity.ok(new MessageResponse(1, "Store image updated successfully!", store.getStoreImageUrl()));
        } catch (IOException e) {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to store file " + fileName + ". Please try again!", null));
        }

    }


}

