package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;

import java.util.Map;

public interface PaymentService {
    Map<String, Object> createPaymentAndTransaction(CreatePaymentRequest request) throws Exception;
    void updatePaymentStatus(Long paymentId, Long transactionId, String status) throws Exception;
}
