package com.Group11.soulfulplates.services.impl;
import com.Group11.soulfulplates.models.*;
import com.Group11.soulfulplates.payload.request.CreatePaymentRequest;
import com.Group11.soulfulplates.payload.response.PaymentFilterResponse;
import com.Group11.soulfulplates.repository.*;
import com.Group11.soulfulplates.services.PaymentService;
import com.Group11.soulfulplates.services.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @InjectMocks
    private PaymentService paymentService = new PaymentServiceImpl();

    private CreatePaymentRequest createPaymentRequest;

    @BeforeEach
    void setUp() {
        createPaymentRequest = new CreatePaymentRequest();
        createPaymentRequest.setUserId(1L);
        createPaymentRequest.setCardNumber("1234567890123456");
        createPaymentRequest.setCardExpiry(String.valueOf(new Date()));
        createPaymentRequest.setCvv(String.valueOf(123));
        createPaymentRequest.setOrderId(1L);
        createPaymentRequest.setAmount(BigDecimal.valueOf(100));
        createPaymentRequest.setStoreId(1L);
    }

    @Test
    void testCreatePaymentAndTransaction_Successful() throws Exception {
        Transaction savedTransaction = new Transaction();
        savedTransaction.setTransactionId(1L);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);

        Payment savedPayment = new Payment();
        savedPayment.setPaymentId(1L);
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        Map<String, Object> response = paymentService.createPaymentAndTransaction(createPaymentRequest);

        assertNotNull(response);
        assertEquals(1L, response.get("Transaction_id"));
        assertEquals(1L, response.get("payment_id"));
    }

    @Test
    void testCreatePaymentAndTransaction_InvalidUserId() {
        createPaymentRequest.setUserId(null);

        assertThrows(Exception.class, () -> paymentService.createPaymentAndTransaction(createPaymentRequest));
    }


    @Test
    void testUpdatePaymentStatus() throws Exception {
        Payment payment = new Payment();
        payment.setPaymentId(1L);
        payment.setTransaction(new Transaction());
        when(paymentRepository.findById(1L)).thenReturn(java.util.Optional.of(payment));

        assertDoesNotThrow(() -> paymentService.updatePaymentStatus(1L, 1L, "Success"));
        assertEquals("Success", payment.getStatus());
    }

    @Test
    void testUpdatePaymentStatus_PaymentNotFound() {
        when(paymentRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(Exception.class, () -> paymentService.updatePaymentStatus(1L, 1L, "Success"));
    }


    @Test
    void testFilterPayments() {
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentId(1L);
        payment.setAmount(BigDecimal.valueOf(100));
        payment.setStatus("Pending");
        payment.setOrder(new Order());
        payment.setStore(new Store());
        payment.setTransaction(new Transaction());
        payments.add(payment);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        when(paymentRepository.findByTransactionUserIdAndStatusOrderByCreatedAtDesc(1L, "Pending", pageRequest))
                .thenReturn(new org.springframework.data.domain.PageImpl<>(payments));

        List<PaymentFilterResponse> response = paymentService.filterPayments(1L, "Pending", 10, 0);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }


    @Test
    void testFilterSellerPayments() {
        List<Payment> payments = new ArrayList<>();
        Payment payment = new Payment();
        payment.setPaymentId(1L);
        payment.setAmount(BigDecimal.valueOf(100));
        payment.setStatus("Pending");
        payment.setOrder(new Order());
        payment.setStore(new Store());
        payment.setTransaction(new Transaction());
        payments.add(payment);

        when(paymentRepository.findByStoreStoreIdAndStatusOrderByCreatedAtDesc(1L, "Pending", null))
                .thenReturn(new org.springframework.data.domain.PageImpl<>(payments));

        List<PaymentFilterResponse> response = paymentService.filterSellerPayments(1L, "Pending", null, null);

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

}
