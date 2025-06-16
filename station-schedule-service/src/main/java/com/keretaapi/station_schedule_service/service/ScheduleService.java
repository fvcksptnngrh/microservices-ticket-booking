package com.keretaapi.station_schedule_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keretaapi.station_schedule_service.dto.ScheduleDTO;
import com.keretaapi.station_schedule_service.entity.Schedule;
import com.keretaapi.station_schedule_service.entity.Station;
import com.keretaapi.station_schedule_service.repository.ScheduleRepository;
import com.keretaapi.station_schedule_service.repository.StationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repo;
    private final StationRepository stationRepo;

    public Schedule save(ScheduleDTO dto) {
        Station from = stationRepo.findById(dto.getStationFromId()).orElse(null);
        Station to = stationRepo.findById(dto.getStationToId()).orElse(null);
        Schedule sch = new Schedule();
        sch.setStationFrom(from);
        sch.setStationTo(to);
        sch.setTrainName(dto.getTrainName());
        sch.setDepartureTime(dto.getDepartureTime());
        sch.setArrivalTime(dto.getArrivalTime());
        sch.setPrice(dto.getPrice());
        return repo.save(sch);
    }
    public List<Schedule> findAll() {
        return repo.findAll();
    }
    public List<Schedule> findByStationFrom(Long id) {
        return repo.findByStationFromId(id);
    }
    public Schedule findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
