package com.Group11.soulfulplates.repository;

import com.Group11.soulfulplates.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderOrderId(Long orderId);
    Optional<Payment> findFirstByOrderOrderIdOrderByPaymentIdDesc(Long orderId);
}
