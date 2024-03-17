package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Map<String, Object> createPaymentAndTransaction(CreatePaymentRequest request) throws Exception;
    void updatePaymentStatus(Long paymentId, Long transactionId, String status) throws Exception;

    List<Map<String, Object>> getBuyerPaymentHistory(Long userId, int limit, int offset, String status) throws Exception;

    List<Map<String, Object>> getSellerPaymentHistory(Long storeId, int limit, int offset, String status) throws Exception;
}
