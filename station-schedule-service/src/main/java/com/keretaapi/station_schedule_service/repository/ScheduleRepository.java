package com.keretaapi.station_schedule_service.repository;

import com.keretaapi.station_schedule_service.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
