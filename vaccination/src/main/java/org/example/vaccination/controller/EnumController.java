package org.example.vaccination.controller;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.dto.EnumDTO;
import org.example.vaccination.service.EnumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "enums")
public class EnumController {

    private final EnumService enumService;

    @GetMapping(path = "/{name}")
    public ResponseEntity<List<EnumDTO>> getEnums(
            @PathVariable String name
    ) {
        List<EnumDTO> enums = enumService.getEnums(name);
        return ResponseEntity.ok(enums);
    }
}
