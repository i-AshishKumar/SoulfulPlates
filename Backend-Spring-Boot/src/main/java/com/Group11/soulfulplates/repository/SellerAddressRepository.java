package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddressRepository extends JpaRepository<SellerAddress, Long> {
}
