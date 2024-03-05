package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.SellerAddress;
import com.Group11.soulfulplates.repository.SellerAddressRepository;
import com.Group11.soulfulplates.services.SellerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerAddressServiceImpl implements SellerAddressService {

    private final SellerAddressRepository sellerAddressRepository;

    @Autowired
    public SellerAddressServiceImpl(SellerAddressRepository sellerAddressRepository) {
        this.sellerAddressRepository = sellerAddressRepository;
    }

    @Override
    public SellerAddress createSellerAddress(SellerAddress sellerAddress) {
        return sellerAddressRepository.save(sellerAddress);
    }

    @Override
    public Optional<SellerAddress> getSellerAddressById(Long id) {
        return sellerAddressRepository.findById(id);
    }

    @Override
    public List<SellerAddress> getAllSellerAddresses() {
        return sellerAddressRepository.findAll();
    }

    @Override
    public SellerAddress updateSellerAddress(SellerAddress sellerAddress) {
        return sellerAddressRepository.save(sellerAddress);
    }

    @Override
    public void deleteSellerAddress(Long id) {
        sellerAddressRepository.deleteById(id);
    }
}
