package com.keretaapi.station_schedule_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keretaapi.station_schedule_service.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByStationFromId(Long stationFromId);
}
