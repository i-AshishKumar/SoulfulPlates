package com.Group11.soulfulplates.services.impl;

import com.Group11.soulfulplates.models.Transaction;
import com.Group11.soulfulplates.repository.TransactionRepository;
import com.Group11.soulfulplates.repository.UserRepository;
import com.Group11.soulfulplates.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository; // Assume this exists

    @Override
    @Transactional
    public Transaction createTransaction(Map<String, Object> request) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setUser(userRepository.findById(Long.valueOf((Integer) request.get("user_id")))
                .orElseThrow(() -> new Exception("User not found")));
        transaction.setCardNumber((String) request.get("card_number"));
        transaction.setCardExpiry((String) request.get("card_expiry")); // Adjust based on your entity's date type
        transaction.setCvv((String) request.get("cvv"));
        transaction.setStatus("Initiated"); // Example status, adjust as necessary

        return transactionRepository.save(transaction);
    }

    @Override
    public void updateTransactionStatus(Long transactionId, String status) throws Exception {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new Exception("Transaction not found"));
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }
}
