package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.State;

import java.time.LocalDate;
import java.util.Set;

public record UserDTO(
        @NotBlank(message = "{name.required}")
        String name,

        @NotNull(message = "{dateOfBirth.required}")
        LocalDate dateOfBirth,

        @NotNull(message = "{gender.required}")
        Character gender,

        @NotBlank(message = "{publicPlace.required}")
        String publicPlace,

        @NotNull(message = "{number.required}")
        Integer number,

        @NotBlank(message = "{district.required}")
        String district,

        @NotBlank(message = "{city.required}")
        String city,

        @NotNull(message = "{state.required}")
        State state,

        Set<Allergy> allergies
) {
}
