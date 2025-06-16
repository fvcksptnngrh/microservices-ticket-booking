package com.keretaapi.station_schedule_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keretaapi.station_schedule_service.dto.StationDTO;
import com.keretaapi.station_schedule_service.entity.Station;
import com.keretaapi.station_schedule_service.service.StationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationController {
    private final StationService service;

    @PostMapping
    public Station create(@RequestBody StationDTO dto) { return service.save(dto); }

    @GetMapping
    public List<Station> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Station getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
