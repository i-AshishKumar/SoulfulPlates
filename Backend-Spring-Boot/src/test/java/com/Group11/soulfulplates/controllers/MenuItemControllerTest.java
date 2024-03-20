package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.controllers.MenuItemController;
import com.Group11.soulfulplates.models.MenuItem;
import com.Group11.soulfulplates.payload.request.MenuItemRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.impl.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testEditMenuItem_MenuItemExists_Success() {
        // Given
        Long menuItemId = 1L;
        MenuItemRequest menuItemRequest = new MenuItemRequest();
        menuItemRequest.setStoreId(1L);
        menuItemRequest.setItemName("Test Item");
        // Set other properties as needed

        MenuItem existingMenuItem = new MenuItem();
        when(menuItemService.findMenuById(menuItemId)).thenReturn(existingMenuItem);

        // When
        MessageResponse response = menuItemController.editMenuItem(menuItemId, menuItemRequest);

        // Then
        assertEquals(1, response.getCode());
        assertEquals("Menu item updated successfully.", response.getDescription());

        // Verify that menuItemService.editMenuItem() is called with the correct arguments
        verify(menuItemService, times(1)).editMenuItem(menuItemId, existingMenuItem);
    }

    @Test
    void testEditMenuItem_MenuItemNotExists_Failure() {
        // Given
        Long menuItemId = 1L;
        MenuItemRequest menuItemRequest = new MenuItemRequest();
        // Mock that the menuItem is not found
        when(menuItemService.findMenuById(menuItemId)).thenReturn(null);

        // When
        MessageResponse response = menuItemController.editMenuItem(menuItemId, menuItemRequest);

        // Then
        assertEquals(0, response.getCode());
        assertEquals("Menu item not found.", response.getDescription());
    }

    // Add more test cases for edge cases and error scenarios
}
