package com.keretaapi.station_schedule_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keretaapi.station_schedule_service.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long> {}
