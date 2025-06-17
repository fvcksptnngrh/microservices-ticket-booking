package com.keretaapi.transaction_service.model;

public enum TransactionStatus {
    AWAITING_PAYMENT,
    PAID,
    CANCELLED,
    FAILED,
    COMPLETED
}