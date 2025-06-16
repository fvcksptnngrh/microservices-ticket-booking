package com.keretaapi.station_schedule_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keretaapi.station_schedule_service.dto.ScheduleDTO;
import com.keretaapi.station_schedule_service.entity.Schedule;
import com.keretaapi.station_schedule_service.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    @PostMapping
    public Schedule create(@RequestBody ScheduleDTO dto) { return service.save(dto); }

    @GetMapping
    public List<Schedule> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Schedule getById(@PathVariable Long id) { return service.findById(id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
