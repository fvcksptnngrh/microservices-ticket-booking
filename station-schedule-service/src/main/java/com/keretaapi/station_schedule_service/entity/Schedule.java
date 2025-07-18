package com.keretaapi.station_schedule_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long stationFromId;
    private Long stationToId;
    private String trainName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;

    // Constructor kosong
    public Schedule() {}

    // --- Getters and Setters Manual ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStationFromId() { return stationFromId; }
    public void setStationFromId(Long stationFromId) { this.stationFromId = stationFromId; }

    public Long getStationToId() { return stationToId; }
    public void setStationToId(Long stationToId) { this.stationToId = stationToId; }

    public String getTrainName() { return trainName; }
    public void setTrainName(String trainName) { this.trainName = trainName; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}