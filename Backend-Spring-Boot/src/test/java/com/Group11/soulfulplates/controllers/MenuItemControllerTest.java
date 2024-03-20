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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuItemControllerTest {

    @Mock
    private MenuItemService menuItemService;

    @InjectMocks
    private MenuItemController menuItemController;
    private MenuItemRequest validMenuItemRequest;
    private Long validMenuItemId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        validMenuItemRequest = new MenuItemRequest();
        validMenuItemRequest.setItemName("Updated Item");
        validMenuItemRequest.setStoreId(1L);
        validMenuItemRequest.setItemPrice(String.valueOf(10.99));
        validMenuItemId = 1L;

    }

    @Test
    void testCreateMenuItem_ValidMenuItem_Success() {
        // Given
        MenuItemRequest menuItemRequest = new MenuItemRequest();
        menuItemRequest.setItemName("Test Item");
        menuItemRequest.setStoreId(1L);

        doNothing().when(menuItemService).createMenuItem(any());

        // When
        MessageResponse response = menuItemController.createMenuItem(menuItemRequest);

        // Then
        assertNotNull(response);
        assertEquals(1, response.getCode());
        assertEquals("Menu item created.", response.getDescription());
        assertNull(response.getData());

        verify(menuItemService).createMenuItem(any());
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

    @Test
    void testEditMenuItem_MenuItemFoundAndUpdated_Success() {
        // Given
        MenuItem menuItem = new MenuItem();
        when(menuItemService.findMenuById(validMenuItemId)).thenReturn(menuItem);

        // When
        MessageResponse response = menuItemController.editMenuItem(validMenuItemId, validMenuItemRequest);

        // Then
        assertNotNull(response);
        assertEquals(1, response.getCode());
        assertEquals("Menu item updated successfully.", response.getDescription());
        assertNull(response.getData());
        verify(menuItemService, times(1)).editMenuItem(validMenuItemId, menuItem);
    }
    @Test
    void testEditMenuItem_MenuItemNotFound_Failure() {
        // Given
        when(menuItemService.findMenuById(validMenuItemId)).thenReturn(null);

        // When
        MessageResponse response = menuItemController.editMenuItem(validMenuItemId, validMenuItemRequest);

        // Then
        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals("Menu item not found.", response.getDescription());
        assertNull(response.getData());
        verify(menuItemService, never()).editMenuItem(any(), any());
    }

    @Test
    void deleteMenuItem_ValidMenuItemId_Success() {
        // When
        MessageResponse response = menuItemController.deleteMenuItem(validMenuItemId);

        // Then
        assertNotNull(response);
        assertEquals(1, response.getCode());
        assertEquals("Menu item deleted successfully.", response.getDescription());
        assertNull(response.getData());
        verify(menuItemService, times(1)).deleteMenuItem(validMenuItemId);
    }

}
