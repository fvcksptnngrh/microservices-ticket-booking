package com.keretaapi.station_schedule_service.controller;

import com.keretaapi.station_schedule_service.dto.ScheduleDTO;
import com.keretaapi.station_schedule_service.entity.Schedule;
import com.keretaapi.station_schedule_service.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public Schedule create(@RequestBody ScheduleDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<Schedule> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Schedule getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
