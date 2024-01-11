package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.example.vaccination.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ScheduleDetailDTO> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ScheduleDetailDTO>> getAllSchedules() {
        List<ScheduleDetailDTO> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> createSchedule(
            @RequestBody @Valid ScheduleDTO scheduleDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);

        var uri = uriBuilder
                .path("/schedule/{id}")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(uri).body(new ScheduleDetailDTO(schedule));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> updateSchedule(
            @RequestBody @Valid ScheduleDetailDTO scheduleDetailDTO
    ) {
        Schedule schedule = scheduleService.updateSchedule(scheduleDetailDTO);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
