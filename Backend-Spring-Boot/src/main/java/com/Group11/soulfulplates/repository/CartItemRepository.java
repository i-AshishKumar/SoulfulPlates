package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // You can define custom methods here, but save, findAll, findById, etc., are provided by JpaRepository.
}
