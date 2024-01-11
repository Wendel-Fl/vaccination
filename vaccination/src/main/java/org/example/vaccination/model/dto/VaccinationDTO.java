package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VaccinationDTO(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Integer dosage,

        @NotNull
        Integer frequency,

        @NotNull
        Integer interval
) {
}
