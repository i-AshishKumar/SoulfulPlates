package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createPaymentAndTransaction")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> createPaymentAndTransaction(@RequestBody(required = false) CreatePaymentRequest request) {
        try {
            Map<String, Object> response = paymentService.createPaymentAndTransaction(request);
            return ResponseEntity.ok().body(Map.of("code", 1, "data", response, "description", "Transaction created."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", -1, "description", "An error occurred: " + e.getMessage()));
        }
    }
}
