package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.Store;

import java.util.List;
import java.util.Optional;
public interface StoreService {
    Store findById(Long storeId);
    Store saveStore(Store store);
    Store updateStoreByUserId(Long userId, Store storeDetails) throws Exception;
}
