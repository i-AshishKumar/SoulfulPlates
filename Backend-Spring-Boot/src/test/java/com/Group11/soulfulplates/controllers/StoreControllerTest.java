package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.services.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

    @Mock
    private StoreRepository storeRepository;

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
    public void testDeleteStore_StoreNotFound() {
        // Mock storeService.existsById to return false
        when(storeService.existsById(anyLong())).thenReturn(false);

        // Invoke controller method
        ResponseEntity<?> responseEntity = storeController.deleteStore(1L);

        // Verify behavior
        verify(storeService, times(1)).existsById(1L);
        verifyNoMoreInteractions(storeService);

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        MessageResponse messageResponse = (MessageResponse) responseEntity.getBody();
        assertEquals(-1, messageResponse.getCode());
        assertEquals("Store Not Found!", messageResponse.getDescription());
        assertEquals(null, messageResponse.getData());
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

    @Test
    public void testUpdateStore_ExceptionCaught() throws Exception {
        // Mock storeService to throw an exception
        when(storeService.updateStoreByUserId(anyLong(), any(Store.class))).thenThrow(new RuntimeException("Store not found"));

        // Invoke controller method
        ResponseEntity<?> responseEntity = storeController.updateStore(1L, new Store());

        // Verify behavior
        verify(storeService, times(1)).updateStoreByUserId(1L, new Store());

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        @SuppressWarnings("unchecked")
        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertEquals(-1, responseBody.get("code"));
        assertEquals("Seller's Store not found", responseBody.get("description"));
    }



}
