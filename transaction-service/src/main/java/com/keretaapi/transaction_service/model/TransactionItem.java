package com.keretaapi.transaction_service.model;

public class TransactionItem {
    private Long scheduleId;
    private String trainName;
    private double price;

    // Getters and Setters
    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
