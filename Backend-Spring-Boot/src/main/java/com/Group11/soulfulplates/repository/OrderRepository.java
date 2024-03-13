package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderIdAndUserId(Long id, Long userId);
}
