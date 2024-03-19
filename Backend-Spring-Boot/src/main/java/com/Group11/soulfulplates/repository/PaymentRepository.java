package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderOrderId(Long orderId);
    Optional<Payment> findFirstByOrderOrderIdOrderByPaymentIdDesc(Long orderId);
    Page<Payment> findByTransactionUserIdAndStatusOrderByCreatedAtDesc(Long userId, String status, Pageable pageable);
}
