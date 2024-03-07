package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.controllers.UserController;
import com.Group11.soulfulplates.models.Address;
import com.Group11.soulfulplates.models.User;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.AddressRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testToggleNotificationFlag_UserExists_NotificationFlagToggled() {
        // Given
        Long userId = (Long) 1L;
        User user = new User();
        user.setId(userId);
        user.setNotificationFlag(false);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // When
        ResponseEntity<MessageResponse> responseEntity = userController.toggleNotificationFlag(userId);

        // Then
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        MessageResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(1, response.getCode());
        assertEquals("Notification flag toggled successfully!", response.getDescription());
        assertTrue(user.isNotificationFlag());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }
    @Test
    void testCreateAddressForUser_UserExists_AddressCreatedSuccessfully() {
        // Given
        Long userId = 1L;
        Address address = new Address();
        address.setAddressId(1L);

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(addressRepository.save(address)).thenReturn(address);

        // When
        ResponseEntity<MessageResponse> responseEntity = userController.createAddressForUser(userId, address);

        // Then
        assertEquals(200, responseEntity.getStatusCodeValue());
        MessageResponse response = responseEntity.getBody();
        assertEquals(1, response.getCode());
        assertEquals("Address saved successfully!", response.getDescription());
        verify(userRepository, times(1)).findById(userId);
        verify(addressRepository, times(1)).save(address);
        assertEquals(user, address.getUser());
    }





}
