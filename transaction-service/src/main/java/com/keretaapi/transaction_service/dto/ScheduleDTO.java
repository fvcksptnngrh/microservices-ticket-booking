package com.keretaapi.transaction_service.dto;


public class ScheduleDTO {
    private Long id;
    private String trainName;
    private double price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}