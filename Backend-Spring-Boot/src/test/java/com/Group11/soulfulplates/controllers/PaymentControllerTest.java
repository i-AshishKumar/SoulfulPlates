package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.UpdatePaymentStatusRequest;
import com.Group11.soulfulplates.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPaymentAndTransaction_Success() throws Exception {
        // Given
        CreatePaymentRequest request = new CreatePaymentRequest();
        Map<String, Object> response = new HashMap<>();
        when(paymentService.createPaymentAndTransaction(request)).thenReturn(response);

        // When
        ResponseEntity<?> responseEntity = paymentController.createPaymentAndTransaction(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(paymentService, times(1)).createPaymentAndTransaction(request);
    }


}

