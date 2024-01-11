package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Vaccination;

public record VaccinationDetailDTO(
        @NotNull
        Long id,

        String title,

        String description,

        Integer dosage,

        Integer frequency,

        Integer interval
) {

    public VaccinationDetailDTO(Vaccination vaccination) {
        this (
                vaccination.getId(),
                vaccination.getTitle(),
                vaccination.getDescription(),
                vaccination.getDosage(),
                vaccination.getFrequency(),
                vaccination.getInterval()
        );
    }
}
