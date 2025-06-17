package com.keretaapi.station_schedule_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StationScheduleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StationScheduleServiceApplication.class, args);
    }
}