package com.Group11.soulfulplates.controllers;


import com.Group11.soulfulplates.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PaymentHistoryControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @Test
    void getRecentPayments_Success() throws Exception {
        // Given
        GetPaymentsRequest request = new GetPaymentsRequest();
        request.setUserId(1L);
        request.setLimit(20);
        request.setOffset(0);
        request.setStatus("completed");

        List<Payment> payments = new ArrayList<>();
        Payment payment1 = new Payment(1L, 1L, 1L, 25.25, 1L, "12**-****-**61", "**/**", "***", "Paid", 1L, 1L, "Paid", LocalDateTime.now(), LocalDateTime.now());
        Payment payment2 = new Payment(1L, 1L, 1L, 25.25, 1L, "12**-****-**61", "**/**", "***", "Paid", 1L, 1L, "Paid", LocalDateTime.now(), LocalDateTime.now());
        payments.add(payment1);
        payments.add(payment2);

        when(paymentService.getRecentPayments(request.getUserId(), request.getLimit(), request.getOffset(), request.getStatus()))
                .thenReturn(payments);

        // When
        ResponseEntity<?> responseEntity = paymentController.getRecentPayments(request);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertEquals(1, responseBody.get("code"));
        assertEquals("Success", responseBody.get("description"));
        assertNotNull(responseBody.get("data"));

        List<Map<String, Object>> responseData = (List<Map<String, Object>>) responseBody.get("data");
        assertEquals(2, responseData.size()); // Ensure correct number of payments in response
        // Add assertions for each payment object in responseData
    }


}

