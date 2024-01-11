package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDTO(
        @NotNull
        LocalDate date,

        @NotNull
        LocalDateTime hour,

        @NotNull
        Status status,

        @NotNull
        LocalDate statusDate,

        @NotBlank
        String notes
) {
}
