package com.keretaapi.transaction_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.keretaapi.transaction_service.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    // Custom query untuk mencari transaksi berdasarkan ID pengguna
    List<Transaction> findByUserId(Long userId);

    // Custom query untuk mencari berdasarkan username (alternatif)
    List<Transaction> findByUsername(String username);
}