package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.models.SellerAddress;
import java.util.List;
import java.util.Optional;

public interface SellerAddressService {
    SellerAddress createSellerAddress(SellerAddress sellerAddress);
    Optional<SellerAddress> getSellerAddressById(Long id);
    List<SellerAddress> getAllSellerAddresses();
    SellerAddress updateSellerAddress(SellerAddress sellerAddress);
    void deleteSellerAddress(Long id);
}
