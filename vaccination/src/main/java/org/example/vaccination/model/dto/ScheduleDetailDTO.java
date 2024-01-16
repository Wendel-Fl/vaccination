package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.Vaccination;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDetailDTO(
        @NotNull
        Long id,

        LocalDate date,

        LocalDateTime hour,

        Status status,

        LocalDate statusDate,

        String notes,

        Vaccination vaccination
) {
    public ScheduleDetailDTO(Schedule schedule) {
        this(
                schedule.getId(),
                schedule.getDate(),
                schedule.getHour(),
                schedule.getStatus(),
                schedule.getStatusDate(),
                schedule.getNotes(),
                schedule.getVaccination()
        );
    }
}
