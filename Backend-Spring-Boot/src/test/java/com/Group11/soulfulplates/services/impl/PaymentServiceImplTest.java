package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePaymentAndTransaction_InvalidUser() {
        // Arrange
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setUserId(null); // Set an invalid user ID
        request.setOrderId(1L);
        request.setStoreId(1L);
        request.setCardNumber("1234567890123456");
        request.setCardExpiry(String.valueOf(new Date()));
        request.setCvv("123");
        request.setAmount(BigDecimal.valueOf(100.0));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            paymentService.createPaymentAndTransaction(request);
        });

        assertEquals("Invalid User Id", exception.getMessage());
    }

}
