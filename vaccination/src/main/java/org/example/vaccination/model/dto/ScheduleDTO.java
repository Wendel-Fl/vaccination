package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.User;
import org.example.vaccination.model.Vaccination;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDTO(
        @NotNull
        LocalDateTime dateTime,

        @NotNull
        Status status,

        @NotNull
        LocalDate statusDate,

        @NotBlank
        String notes,

        Vaccination vaccination,

        User user
) {
}
