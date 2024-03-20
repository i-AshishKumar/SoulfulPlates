package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePaymentAndTransaction_InvalidUser() {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setUserId(null);
        request.setOrderId(1L);
        request.setStoreId(1L);
        request.setCardNumber("1234567890123456");
        request.setCardExpiry(String.valueOf(new Date()));
        request.setCvv("123");
        request.setAmount(BigDecimal.valueOf(100.0));

        try {
            paymentService.createPaymentAndTransaction(request);
        } catch (Exception e) {
            assertEquals("Invalid User Id", e.getMessage());
        }
    }

}
