package com.Group11.soulfulplates.controllers;

import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.request.UpdatePaymentStatusRequest;
import com.Group11.soulfulplates.payload.response.MessageResponse;
import com.Group11.soulfulplates.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/updateStatus")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> updatePaymentStatus(@RequestBody UpdatePaymentStatusRequest request) {
        try {
            paymentService.updatePaymentStatus(request.getPaymentId(), request.getTransactionId(), request.getStatus());
            return ResponseEntity.ok(new MessageResponse(1, "Payment status updated.", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(0, "Error updating payment status: " + e.getMessage(), null));
        }
    }
    @GetMapping("/buyerPaymentHistory")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<?> getBuyerPaymentHistory(
            @RequestParam Long userId,
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam(required = false) String status
    ) {
        try {
            List<Map<String, Object>> payments = paymentService.getBuyerPaymentHistory(userId, limit, offset, status);
            return ResponseEntity.ok().body(Map.of("code", 1, "description", "Success", "data", payments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", -1, "description", "Error: " + e.getMessage()));
        }
    }

    @GetMapping("/sellerPaymentHistory")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<?> getSellerPaymentHistory(
            @RequestParam Long storeId,
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam(required = false) String status
    ) {
        try {
            List<Map<String, Object>> payments = paymentService.getSellerPaymentHistory(storeId, limit, offset, status);
            return ResponseEntity.ok().body(Map.of("code", 1, "description", "Success", "data", payments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", -1, "description", "Error: " + e.getMessage()));
        }
    }

}
