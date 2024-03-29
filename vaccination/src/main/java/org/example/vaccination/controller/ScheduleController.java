package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.example.vaccination.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

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
    public ResponseEntity<Page<ScheduleDetailDTO>> getAllSchedules(
            Pageable pageable,

            @RequestParam(name = "status", required = false)
            Status status,

            @RequestParam(name = "initialDate", required = false)
            @DateTimeFormat(iso = DATE_TIME)
            LocalDateTime initialDate,

            @RequestParam(name = "finalDate", required = false)
            @DateTimeFormat(iso = DATE_TIME)
            LocalDateTime finalDate
    ) {
        Page<ScheduleDetailDTO> schedules = scheduleService
                .getAllSchedules(pageable, status, initialDate, finalDate);

        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<List<ScheduleDetailDTO>> createSchedule(
            @RequestBody @Valid ScheduleDTO scheduleDTO
    ) {
        List<Schedule> schedules = scheduleService.createSchedule(scheduleDTO);

        List<ScheduleDetailDTO> scheduleDetailsDTO = schedules
                .stream()
                .map(ScheduleDetailDTO::new)
                .toList();

        return ResponseEntity.ok(scheduleDetailsDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> updateSchedule(
            @RequestBody @Valid ScheduleDetailDTO scheduleDetailDTO
    ) {
        Schedule schedule = scheduleService.updateSchedule(scheduleDetailDTO);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }

    @PutMapping(path = "/schedule-cancelled")
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> scheduleCancelled(
            @RequestBody @Valid ScheduleDetailDTO scheduleDetailDTO
    ) {
        Schedule schedule = scheduleService.scheduleCancelled(scheduleDetailDTO);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }

    @PutMapping(path = "/schedule-carried-out")
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> scheduleCarriedOut(
            @RequestBody @Valid ScheduleDetailDTO scheduleDetailDTO
    ) {
        Schedule schedule = scheduleService.scheduleCarriedOut(scheduleDetailDTO);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }

    @PutMapping(path = "attach-vaccination")
    @Transactional
    public ResponseEntity<ScheduleDetailDTO> attachVaccination(
            @RequestBody @Valid ScheduleDetailDTO scheduleDetailDTO
    ) {
        Schedule schedule = scheduleService.attachVaccination(scheduleDetailDTO);
        return ResponseEntity.ok(new ScheduleDetailDTO(schedule));
    }



    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Schedule> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
