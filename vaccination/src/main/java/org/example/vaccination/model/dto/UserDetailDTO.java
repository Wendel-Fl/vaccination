package org.example.vaccination.model.dto;

import jakarta.validation.constraints.NotNull;
import org.example.vaccination.model.State;
import org.example.vaccination.model.User;

import java.time.LocalDate;

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

        State state
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
                user.getState()
        );
    }
}
