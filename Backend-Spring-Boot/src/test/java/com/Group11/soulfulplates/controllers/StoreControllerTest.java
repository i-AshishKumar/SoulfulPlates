package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

    @Mock
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @Test
    void testCreateStore() {
        Store store = new Store();
        store.setStoreId(1L);

        when(storeService.createStore(any())).thenReturn(store);

        ResponseEntity<?> response = storeController.createStore(store);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetStoreById() {
        Store store = new Store();
        store.setStoreId(1L);

        when(storeService.getStoreById(1L)).thenReturn(java.util.Optional.of(store));

        ResponseEntity<?> response = storeController.getStoreById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetAllStores() {
        when(storeService.getAllStores()).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = storeController.getAllStores();

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteStore() {
        when(storeService.existsById(1L)).thenReturn(true);

        ResponseEntity<?> response = storeController.deleteStore(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateStore() throws Exception {
        Store store = new Store();
        store.setStoreId(1L);

        when(storeService.updateStoreByUserId(1L, store)).thenReturn(store);

        ResponseEntity<?> response = storeController.updateStore(1L, store);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testGetStoreById_NotFound() {
        // Mocking the service method
        when(storeService.getStoreById(anyLong())).thenReturn(Optional.empty());

        // Calling the controller method
        ResponseEntity<?> responseEntity = storeController.getStoreById(1L);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("No Store Found", ((MessageResponse) responseEntity.getBody()).getDescription());
    }

}
