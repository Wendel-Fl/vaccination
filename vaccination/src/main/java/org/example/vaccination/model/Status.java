package org.example.vaccination.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    SCHEDULED("Agendado"),
    CANCELLED("Cancelado"),
    CARRIED_OUT("Realizado");

    private final String description;

    public static Status fromStatus(final String nameStatus) {
        for (final Status status : Status.values()) {
            if (status.description.equals(nameStatus)) {
                return status;
            }
        }

        throw new IllegalArgumentException(nameStatus);
    }
}
