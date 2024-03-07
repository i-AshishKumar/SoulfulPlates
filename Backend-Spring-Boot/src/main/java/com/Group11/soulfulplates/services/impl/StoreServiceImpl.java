package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.repository.StoreRepo;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.services.StoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepo storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepo storeRepo) {
        this.storeRepository = storeRepo;
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    @Transactional
    public Store updateStoreByUserId(Long userId, Store storeDetails) throws Exception {
        Store store = storeRepository.findByUser_Id(userId)
                .orElseThrow(() -> new Exception("Store not found for this user: " + userId));

        store.setStoreName(storeDetails.getStoreName());
        store.setStoreEmail(storeDetails.getStoreEmail());
        store.setContactNumber(storeDetails.getContactNumber());
        store.setStoreDescription(storeDetails.getStoreDescription());

        return storeRepository.save(store);
    }

}
