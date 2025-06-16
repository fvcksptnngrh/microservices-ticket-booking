package com.keretaapi.station_schedule_service.repository;

import com.keretaapi.station_schedule_service.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
