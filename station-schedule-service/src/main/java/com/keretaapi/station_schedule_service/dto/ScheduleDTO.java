package com.keretaapi.station_schedule_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDTO {
    private Long id;
    private Long stationFromId;
    private Long stationToId;
    private String trainName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
}