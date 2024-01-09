package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.State;

import java.time.LocalDate;

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
        State state
) {
}
