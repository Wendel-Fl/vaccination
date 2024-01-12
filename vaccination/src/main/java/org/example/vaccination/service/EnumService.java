package org.example.vaccination.service;

import org.example.vaccination.model.State;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.dto.EnumDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EnumService {

    public List<EnumDTO> getEnums(String name) {
        return getEnumList(name);
    }

    private List<EnumDTO> getEnumList(String name) {
        return switch (name) {
            case ("state") -> Arrays
                    .stream(State.values())
                    .map(e -> new EnumDTO(e.name(), e.getAcronym()))
                    .toList();

            case ("status") -> Arrays
                    .stream(Status.values())
                    .map(e -> new EnumDTO(e.name(), e.getDescription()))
                    .toList();

            default -> new ArrayList<>();
        };
    }
}
