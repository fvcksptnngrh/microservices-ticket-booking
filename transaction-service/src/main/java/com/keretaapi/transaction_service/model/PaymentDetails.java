package com.keretaapi.transaction_service.model;

import java.time.LocalDateTime;

public class PaymentDetails {
    private String method;
    private double paidAmount;
    private LocalDateTime paymentDate;

    // Getters and Setters
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }
    
}
