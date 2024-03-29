package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AllergyDTO(
        @NotBlank(message = "{name.required}")
        String name
) {
}
