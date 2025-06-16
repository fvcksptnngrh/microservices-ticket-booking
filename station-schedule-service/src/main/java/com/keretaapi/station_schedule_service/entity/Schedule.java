package com.keretaapi.station_schedule_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "station_from_id")
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "station_to_id")
    private Station stationTo;

    private String trainName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
}
