package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Store;
import com.Group11.soulfulplates.repository.StoreRepository;
import com.Group11.soulfulplates.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    //    @Autowired
//    public StoreServiceImpl(StoreRepository storeRepository) {
//        this.storeRepository = storeRepository;
//    }

    @Override
    public Store createStore(Store seller) {
        return storeRepository.save(seller);
    }

    @Override
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store updateStore(Store seller) {
        return storeRepository.save(seller);
    }

    @Override
    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }
}
