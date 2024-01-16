package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.State;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserDTO(
        @NotBlank
        String name,

        @NotNull
        LocalDate dateOfBirth,

        @NotNull
        Character gender,

        @NotBlank
        String publicPlace,

        @NotNull
        Integer number,

        @NotBlank
        String district,

        @NotBlank
        String city,

        @NotNull
        State state,

        Set<Allergy> allergies,

        List<Schedule> schedules
) {
}
