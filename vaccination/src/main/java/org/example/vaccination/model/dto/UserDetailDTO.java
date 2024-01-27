package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.State;
import org.example.vaccination.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserDetailDTO(
        @NotNull
        Long id,

        String name,

        LocalDate dateOfBirth,

        Character gender,

        String publicPlace,

        Integer number,

        String district,

        String city,

        State state,

        Set<Allergy> allergies
) {
    public UserDetailDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getPublicPlace(),
                user.getNumber(),
                user.getDistrict(),
                user.getCity(),
                user.getState(),
                user.getAllergies()
        );
    }
}
