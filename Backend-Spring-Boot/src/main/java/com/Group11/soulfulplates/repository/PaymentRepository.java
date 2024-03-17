package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderOrderId(Long orderId);
    Optional<Payment> findFirstByOrderOrderIdOrderByPaymentIdDesc(Long orderId);

    Page<Payment> findByOrderUserUserId(Long userId, Pageable pageable);
    Page<Payment> findByOrderUserUserIdAndStatus(Long userId, String status, Pageable pageable);

    Page<Payment> findByStoreStoreId(Long storeId, Pageable pageable);
    Page<Payment> findByStoreStoreIdAndStatus(Long storeId, String status, Pageable pageable);
}
