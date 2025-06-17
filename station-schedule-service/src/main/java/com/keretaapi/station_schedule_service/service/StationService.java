package com.keretaapi.station_schedule_service.service;

import com.keretaapi.station_schedule_service.dto.StationDTO;
import com.keretaapi.station_schedule_service.entity.Station;
import com.keretaapi.station_schedule_service.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    
    @Autowired
    private StationRepository repo;

    public Station save(StationDTO dto) {
        Station s = new Station();
        if (dto.getId() != null) {
            s = repo.findById(dto.getId()).orElse(new Station());
        }
        s.setCode(dto.getCode()); // Sekarang valid
        s.setName(dto.getName());
        s.setCity(dto.getCity());
        return repo.save(s);
    }

    public List<Station> findAll() {
        return repo.findAll();
    }

    public Station findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}