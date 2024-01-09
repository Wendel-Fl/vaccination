package org.example.vaccination.model.dto;

import org.example.vaccination.model.User;

import java.time.LocalDate;

public record UserDetailDTO(
        Long id,
        String name,
        LocalDate dateOfBirth,
        Character gender,
        String publicPlace,
        Integer number,
        String district,
        String city,
        String state
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
                user.getState().name()
        );
    }
}
