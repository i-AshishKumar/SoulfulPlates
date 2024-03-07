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

    @Test
    void testGetAllAddressesForUser_UserExists_AddressesFetchedSuccessfully() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        List<Address> addresses = new ArrayList<>();
        Address address1 = new Address();
        address1.setAddressId(1L);
        address1.setStreet("123 Main St");
        address1.setCity("City");
        address1.setState("State");
        address1.setPostalCode("12345");
        address1.setCountry("Country");
        address1.setUser(user);
        addresses.add(address1);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(addressRepository.findByUser(user)).thenReturn(addresses);

        // When
        ResponseEntity<MessageResponse> responseEntity = userController.getAllAddressesForUser(userId);

        // Then
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        MessageResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(1, response.getCode());
        assertEquals("Addresses fetched successfully!", response.getDescription());

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode responseData = (ArrayNode) response.getData();
        assertEquals(1, responseData.size());

        ObjectNode addressNode = (ObjectNode) responseData.get(0);
        assertNull(addressNode.get("user"));
        assertEquals("123 Main St", addressNode.get("street").asText());
        assertEquals("City", addressNode.get("city").asText());
        assertEquals("State", addressNode.get("state").asText());
        assertEquals("12345", addressNode.get("postalCode").asText());
        assertEquals("Country", addressNode.get("country").asText());

        verify(userRepository, times(1)).findById(userId);
        verify(addressRepository, times(1)).findByUser(user);
    }

}
