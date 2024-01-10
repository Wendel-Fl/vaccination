package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Allergy;

public record AllergyDetailDTO(
        @NotNull
        Long id,

        String name
) {

    public AllergyDetailDTO(Allergy allergy) {
        this(allergy.getId(), allergy.getName());
    }
}
