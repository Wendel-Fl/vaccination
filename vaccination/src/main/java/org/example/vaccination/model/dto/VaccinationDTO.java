package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VaccinationDTO(
        @NotBlank(message = "{title.required}")
        String title,

        String description,

        @NotNull(message = "{dosage.required}")
        Integer dosage,

        @NotNull(message = "{frequency.required}")
        Integer frequency,

        @NotNull(message = "{interval.required}")
        Integer interval
) {
}
