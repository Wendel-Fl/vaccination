package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.dto.AllergyDTO;
import org.example.vaccination.model.dto.AllergyDetailDTO;
import org.example.vaccination.service.AllergyService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "allergy")
public class AllergyController {

    private final AllergyService allergyService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<AllergyDetailDTO> getAllergy(@PathVariable Long id) {
        Allergy allergy = allergyService.getAllergyById(id);
        return ResponseEntity.ok(new AllergyDetailDTO(allergy));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<AllergyDetailDTO>> getAllAllergies() {
        List<AllergyDetailDTO> allergies = allergyService.getAllAllergies();
        return ResponseEntity.ok(allergies);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AllergyDetailDTO> createAllergy(
            @RequestBody @Valid AllergyDTO allergyDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Allergy allergy = allergyService.createAllergy(allergyDTO);

        var uri = uriBuilder
                .path("/allergy/{id}")
                .buildAndExpand(allergy.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new AllergyDetailDTO(allergy));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AllergyDetailDTO> updateAllergy(AllergyDetailDTO allergyDetailDTO) {
        Allergy allergy = allergyService.updateAllergy(allergyDetailDTO);
        return ResponseEntity.ok(new AllergyDetailDTO(allergy));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Allergy> deleteAllergy(@PathVariable Long id) {
        allergyService.deleteAllergy(id);
        return ResponseEntity.noContent().build();
    }
}
