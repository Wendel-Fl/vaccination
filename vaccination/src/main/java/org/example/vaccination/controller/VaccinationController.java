package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Vaccination;
import org.example.vaccination.model.dto.VaccinationDTO;
import org.example.vaccination.model.dto.VaccinationDetailDTO;
import org.example.vaccination.service.VaccinationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "vaccination")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<VaccinationDetailDTO> getVaccine(@PathVariable Long id) {
        Vaccination vaccination = vaccinationService.getVaccineById(id);
        return ResponseEntity.ok(new VaccinationDetailDTO(vaccination));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Page<VaccinationDetailDTO>> getAllVaccines(
            @PageableDefault(sort = {"id"}) Pageable pageable,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "dosage", required = false) Integer dosage,
            @RequestParam(name = "frequency", required = false) Integer frequency,
            @RequestParam(name = "interval", required = false) Integer interval
    ) {
        Page<VaccinationDetailDTO> vaccines = vaccinationService
                .getAllVaccines(
                        pageable,
                        title,
                        description,
                        dosage,
                        frequency,
                        interval
                );
        return ResponseEntity.ok(vaccines);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VaccinationDetailDTO> createVaccination(
            @RequestBody @Valid VaccinationDTO vaccinationDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Vaccination vaccination = vaccinationService.createVaccine(vaccinationDTO);

        var uri = uriBuilder
                .path("/vaccination/{id}")
                .buildAndExpand(vaccination.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new VaccinationDetailDTO(vaccination));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<VaccinationDetailDTO> updateVaccination(
            @RequestBody @Valid VaccinationDetailDTO vaccinationDetailDTO
    ) {
        Vaccination vaccination = vaccinationService.updateVaccine(vaccinationDetailDTO);
        return ResponseEntity.ok(new VaccinationDetailDTO(vaccination));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<VaccinationDetailDTO> deleteVaccination(
            @PathVariable Long id
    ) {
        vaccinationService.deleteVaccine(id);
        return ResponseEntity.noContent().build();
    }
}
