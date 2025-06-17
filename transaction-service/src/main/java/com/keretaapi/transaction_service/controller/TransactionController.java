package com.keretaapi.transaction_service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.keretaapi.transaction_service.dto.CreateTransactionRequest;
import com.keretaapi.transaction_service.model.Transaction;
import com.keretaapi.transaction_service.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody CreateTransactionRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        Transaction transaction = transactionService.createTransaction(request, authorizationHeader);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/my-history")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Transaction>> getMyHistory() {
        List<Transaction> transactions = transactionService.findTransactionsForCurrentUser();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactions);
    }

@PostMapping("/{transactionId}/pay")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public ResponseEntity<?> payTransaction(
        @PathVariable String transactionId,
        @RequestBody Map<String, String> body) {
    String paymentMethod = body.getOrDefault("paymentMethod", "bank-transfer");
    transactionService.payTransaction(transactionId, paymentMethod);
    return ResponseEntity.ok("Pembayaran berhasil!");
    }
}