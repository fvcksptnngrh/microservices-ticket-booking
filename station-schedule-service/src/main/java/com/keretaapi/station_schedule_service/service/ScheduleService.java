package com.keretaapi.station_schedule_service.service;

import com.keretaapi.station_schedule_service.dto.ScheduleDTO;
import com.keretaapi.station_schedule_service.entity.Schedule;
import com.keretaapi.station_schedule_service.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleRepository repo;

    public Schedule save(ScheduleDTO dto) {
        Schedule s = new Schedule();
        s.setStationFromId(dto.getStationFromId());
        s.setStationToId(dto.getStationToId());
        s.setTrainName(dto.getTrainName());
        s.setDepartureTime(dto.getDepartureTime());
        s.setArrivalTime(dto.getArrivalTime());
        s.setPrice(dto.getPrice());
        return repo.save(s);
    }

    public List<Schedule> findAll() {
        return repo.findAll();
    }

    public Schedule findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}