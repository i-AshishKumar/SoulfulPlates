package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Payment;
import com.Group11.soulfulplates.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBuyerPaymentHistory_Success() throws Exception {
        // Given
        Long userId = 1L;
        int limit = 20;
        int offset = 0;
        String status = "completed";

        List<Payment> payments = new ArrayList<>();


        Page<Payment> paymentPage = new PageImpl<>(payments);

        when(paymentRepository.findByOrderUserUserIdAndStatus(userId, status, PageRequest.of(0, limit, Sort.by("paymentId").descending())))
                .thenReturn(paymentPage);

        // When
        List<Map<String, Object>> result = paymentService.getBuyerPaymentHistory(userId, limit, offset, status);

        // Then
        assertEquals(payments.size(), result.size());

    }

    @Test
    void getSellerPaymentHistory_Success() throws Exception {
        // Given
        Long storeId = 1L;
        int limit = 20;
        int offset = 0;
        String status = "completed";

        List<Payment> payments = new ArrayList<>();
        // Add some sample payments to the list

        Page<Payment> paymentPage = new PageImpl<>(payments);

        when(paymentRepository.findByStoreStoreIdAndStatus(storeId, status, PageRequest.of(0, limit, Sort.by("paymentId").descending())))
                .thenReturn(paymentPage);

        // When
        List<Map<String, Object>> result = paymentService.getSellerPaymentHistory(storeId, limit, offset, status);

        // Then
        assertEquals(payments.size(), result.size());
        // Add more assertions if needed
    }

    @Test
    void getBuyerPaymentHistory_EmptyResult() throws Exception {
        // Given
        Long userId = 1L;
        int limit = 20;
        int offset = 0;
        String status = "completed";

        Page<Payment> paymentPage = new PageImpl<>(new ArrayList<>());

        when(paymentRepository.findByOrderUserUserIdAndStatus(userId, status, PageRequest.of(offset / limit, limit, Sort.by("paymentId").descending())))
                .thenReturn(paymentPage);

        // When
        List<Map<String, Object>> result = paymentService.getBuyerPaymentHistory(userId, limit, offset, status);

        // Then
        assertEquals(0, result.size());
    }

    @Test
    void getSellerPaymentHistory_EmptyResult() throws Exception {
        // Given
        Long storeId = 1L;
        int limit = 20;
        int offset = 0;
        String status = "completed";

        Page<Payment> paymentPage = new PageImpl<>(new ArrayList<>());

        when(paymentRepository.findByStoreStoreIdAndStatus(storeId, status, PageRequest.of(offset / limit, limit, Sort.by("paymentId").descending())))
                .thenReturn(paymentPage);

        // When
        List<Map<String, Object>> result = paymentService.getSellerPaymentHistory(storeId, limit, offset, status);

        // Then
        assertEquals(0, result.size());
    }

}
