package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.User;
import org.example.vaccination.model.Vaccination;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDetailDTO(
        @NotNull
        Long id,

        LocalDateTime dateTime,

        Status status,

        LocalDate statusDate,

        String notes,

        Vaccination vaccination,

        User user
) {
    public ScheduleDetailDTO(Schedule schedule) {
        this(
                schedule.getId(),
                schedule.getDateTime(),
                schedule.getStatus(),
                schedule.getStatusDate(),
                schedule.getNotes(),
                schedule.getVaccination(),
                schedule.getUser()
        );
    }
}
