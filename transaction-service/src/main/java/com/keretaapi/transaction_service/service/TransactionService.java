package com.keretaapi.transaction_service.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.keretaapi.transaction_service.dto.CreateTransactionRequest;
import com.keretaapi.transaction_service.dto.ScheduleDTO;
import com.keretaapi.transaction_service.dto.UserDTO;
import com.keretaapi.transaction_service.model.PaymentDetails;
import com.keretaapi.transaction_service.model.Transaction;
import com.keretaapi.transaction_service.model.TransactionItem;
import com.keretaapi.transaction_service.model.TransactionStatus;
import com.keretaapi.transaction_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Transaction createTransaction(CreateTransactionRequest request, String token) {
       
        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        UserDTO user = webClientBuilder.build().get()
                .uri("http://user-service/api/users/profile")
                .header("Authorization", token) 
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block(); 
        
        ScheduleDTO schedule = webClientBuilder.build().get()
                .uri("http://station-schedule-service/api/schedules/{id}", request.getScheduleId())
                .retrieve()
                .bodyToMono(ScheduleDTO.class)
                .block();

        if (schedule == null || user == null) {
            throw new RuntimeException("Jadwal atau User tidak valid.");
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionCode("KAI-TRX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        transaction.setUserId(user.getId());
        transaction.setUsername(username);
        transaction.setStatus(TransactionStatus.AWAITING_PAYMENT);
        
        TransactionItem item = new TransactionItem();
        item.setScheduleId(schedule.getId());
        item.setTrainName(schedule.getTrainName());
        item.setPrice(schedule.getPrice());
        
        transaction.setItems(Collections.singletonList(item));
        transaction.setTotalPrice(schedule.getPrice());
        
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findTransactionsForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return transactionRepository.findByUsername(username);
    }
    
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

public void payTransaction(String transactionId, String paymentMethod) {
    Transaction tx = transactionRepository.findById(transactionId)
        .orElseThrow(() -> new RuntimeException("Transaksi tidak ditemukan"));
    if (tx.getStatus() != TransactionStatus.AWAITING_PAYMENT) {
        throw new RuntimeException("Transaksi sudah dibayar atau status tidak valid");
    }
    tx.setStatus(TransactionStatus.PAID);


    PaymentDetails details = new PaymentDetails();
    details.setMethod(paymentMethod); // contoh: 
    details.setPaidAmount(tx.getTotalPrice());
    details.setPaymentDate(LocalDateTime.now());
    tx.setPaymentDetails(details);

    transactionRepository.save(tx);
}
}
