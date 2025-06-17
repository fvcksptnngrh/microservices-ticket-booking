package com.keretaapi.station_schedule_service.controller;

import com.keretaapi.station_schedule_service.dto.StationDTO;
import com.keretaapi.station_schedule_service.entity.Station;
import com.keretaapi.station_schedule_service.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Station create(@RequestBody StationDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Station update(@PathVariable Long id, @RequestBody StationDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @GetMapping // Ini akan diizinkan oleh SecurityConfig
    public List<Station> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}") // Ini juga akan diizinkan oleh SecurityConfig
    public Station getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}