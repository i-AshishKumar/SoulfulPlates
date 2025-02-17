package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    boolean existsById(Long id);

    public Store createStore(Store seller);

    public Optional<Store> getStoreById(Long id);

    public List<Store> getAllStores();

//    public Store updateStore(Store seller);

    public void deleteStore(Long id);

//    Store saveStore(Store store);

    Store updateStoreByUserId(Long userId, Store storeDetails) throws Exception;
}

