package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/toggle-notification/{userId}")
    public ResponseEntity<MessageResponse> toggleNotificationFlag(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Toggle the value of notificationFlag
        user.setNotificationFlag(!user.isNotificationFlag());

        User updatedUser = userRepository.save(user);

        if (updatedUser != null) {
            return ResponseEntity.ok(new MessageResponse(1, "Notification flag toggled successfully!", null));
        } else {
            return ResponseEntity.ok(new MessageResponse(-1, "Failed to toggle notification flag!", null));
        }
    }
}
