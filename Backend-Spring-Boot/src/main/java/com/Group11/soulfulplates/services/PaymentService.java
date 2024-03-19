package com.Group11.soulfulplates.services;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.PaymentFilterRequest;
import com.Group11.soulfulplates.payload.response.PaymentFilterResponse;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Map<String, Object> createPaymentAndTransaction(CreatePaymentRequest request) throws Exception;
    void updatePaymentStatus(Long paymentId, Long transactionId, String status) throws Exception;
    List<PaymentFilterResponse> filterPayments(Long userId, String status, Integer limit, Integer offset);
}
