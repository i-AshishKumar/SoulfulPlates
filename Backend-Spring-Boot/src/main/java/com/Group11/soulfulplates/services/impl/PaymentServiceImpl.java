package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Payment;
import com.Group11.soulfulplates.models.Transaction;
import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.repository.*;
import com.Group11.soulfulplates.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository; // Assume this exists

    @Autowired
    private UserRepository userRepository; // Assume this exists

    @Autowired
    private StoreRepository storeRepository; // Assume this exists

    @Override
    @Transactional
    public Map<String, Object> createPaymentAndTransaction(CreatePaymentRequest request) throws Exception {
        Transaction transaction = new Transaction();
        if(request.getUserId() != null && request.getUserId() > 0){
            transaction.setUser(userRepository.findById(request.getUserId()).orElseThrow(() -> new Exception("User not found")));
        } else {
            throw new Exception("Invalid User Id");
        }
        transaction.setCardNumber(request.getCardNumber());
        transaction.setCardExpiry(request.getCardExpiry()); // Adjust for your model
        transaction.setCvv(request.getCvv());
        transaction.setStatus("Processing");

        if(request.getOrderId() == null && request.getOrderId() <= 0) {
            throw new Exception("Invalid Order Id");
        }

        Transaction savedTransaction = transactionRepository.save(transaction);

        if(savedTransaction.getTransactionId()!=null){
            Payment payment = new Payment();
            payment.setTransaction(savedTransaction);
            payment.setOrder(orderRepository.findById(request.getOrderId()).orElseThrow(() -> new Exception("Order not found")));
            payment.setAmount(request.getAmount());
            payment.setStatus("Pending");
            if(request.getStoreId() != null && request.getStoreId() > 0) {
                payment.setStore(storeRepository.findById(request.getStoreId()).orElseThrow(() -> new Exception("Store not found")));
            } else {
                throw new Exception("Invalid Store Id");
            }
            Payment savedPayment = paymentRepository.save(payment);
            Map<String, Object> response = new HashMap<>();
            response.put("Transaction_id", savedTransaction.getTransactionId());
            response.put("payment_id", savedPayment.getPaymentId());
            return response;
        } else {
            throw new Exception("Invalid Transaction Id");
        }
    }
}
