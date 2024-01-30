package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.User;
import org.example.vaccination.model.Vaccination;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleDTO(
        @NotNull(message = "{dateTime.required}")
        LocalDateTime dateTime,

        String notes,

        @NotNull(message = "{vaccination.required}")
        Vaccination vaccination,

        @NotNull(message = "{user.required}")
        User user
) {
}
