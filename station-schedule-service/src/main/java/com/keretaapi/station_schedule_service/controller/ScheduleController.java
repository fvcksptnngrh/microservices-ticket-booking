package com.keretaapi.station_schedule_service.controller;

import com.keretaapi.station_schedule_service.dto.ScheduleDTO;
import com.keretaapi.station_schedule_service.entity.Schedule;
import com.keretaapi.station_schedule_service.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @PostMapping
    @PreAuthorize("isAuthenticated()") // Bisa ADMIN atau USER
    public Schedule create(@RequestBody ScheduleDTO dto) {
        return service.save(dto);
    }

    @GetMapping // Diizinkan oleh SecurityConfig
    public List<Schedule> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}") // Diizinkan oleh SecurityConfig
    public Schedule getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}