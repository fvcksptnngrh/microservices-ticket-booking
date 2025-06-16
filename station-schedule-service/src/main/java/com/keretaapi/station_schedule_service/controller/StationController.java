package com.keretaapi.station_schedule_service.controller;

import com.keretaapi.station_schedule_service.dto.StationDTO;
import com.keretaapi.station_schedule_service.entity.Station;
import com.keretaapi.station_schedule_service.service.StationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService service;

    public StationController(StationService service) {
        this.service = service;
    }

    @PostMapping
    public Station create(@RequestBody StationDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<Station> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Station getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
