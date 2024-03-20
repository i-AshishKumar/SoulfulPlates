package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class StoreServiceImplTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateStoreByUserId_Success() throws Exception {
        Long userId = 1L;
        Store existingStore = new Store();
        existingStore.setStoreId(1L);
        existingStore.setStoreName("Old Store Name");
        existingStore.setStoreEmail("old@example.com");
        existingStore.setContactNumber("1234567890");
        existingStore.setStoreDescription("Old Store Description");

        Store updatedStoreDetails = new Store();
        updatedStoreDetails.setStoreName("New Store Name");
        updatedStoreDetails.setStoreEmail("new@example.com");
        updatedStoreDetails.setContactNumber("9876543210");
        updatedStoreDetails.setStoreDescription("New Store Description");

        Mockito.when(storeRepository.findByUser_Id(userId)).thenReturn(Optional.of(existingStore));
        Mockito.when(storeRepository.save(Mockito.any(Store.class))).thenReturn(updatedStoreDetails);

        Store updatedStore = storeService.updateStoreByUserId(userId, updatedStoreDetails);

        Assertions.assertEquals(updatedStoreDetails.getStoreName(), updatedStore.getStoreName());
        Assertions.assertEquals(updatedStoreDetails.getStoreEmail(), updatedStore.getStoreEmail());
        Assertions.assertEquals(updatedStoreDetails.getContactNumber(), updatedStore.getContactNumber());
        Assertions.assertEquals(updatedStoreDetails.getStoreDescription(), updatedStore.getStoreDescription());
    }

    @Test
    public void testCreateStore_Success() {
        Store store = new Store();
        store.setStoreId(1L);
        store.setStoreName("Test Store");
        store.setStoreEmail("test@example.com");
        store.setContactNumber("1234567890");
        store.setStoreDescription("Test Store Description");

        Mockito.when(storeRepository.save(Mockito.any(Store.class))).thenReturn(store);

        Store createdStore = storeService.createStore(store);

        Assertions.assertEquals(store, createdStore);
    }

    // Add more test cases for other methods (e.g., getStoreById, getAllStores, deleteStore, existsById)
}
