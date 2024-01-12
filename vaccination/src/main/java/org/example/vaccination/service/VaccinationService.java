package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Vaccination;
import org.example.vaccination.model.dto.VaccinationDTO;
import org.example.vaccination.model.dto.VaccinationDetailDTO;
import org.example.vaccination.repository.VaccinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private static final String VACCINATION_NOT_FOUND = "Vaccination not found";

    private final VaccinationRepository vaccinationRepository;

    public Vaccination getVaccineById(Long id) {
        Boolean exists = vaccinationRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        return vaccinationRepository.getReferenceById(id);
    }

    public List<VaccinationDetailDTO> getAllVaccines() {
        return vaccinationRepository
                .findAll()
                .stream()
                .map(VaccinationDetailDTO::new)
                .toList();
    }

    public Vaccination createVaccine(VaccinationDTO vaccinationDTO) {
        Vaccination vaccination = new Vaccination(vaccinationDTO);
        return vaccinationRepository.save(vaccination);
    }

    public Vaccination updateVaccine(VaccinationDetailDTO vaccinationDetailDTO) {
        Boolean exists = vaccinationRepository.existsById(vaccinationDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        Vaccination vaccination = vaccinationRepository
                .getReferenceById(vaccinationDetailDTO.id());

        vaccination.updateVaccine(vaccinationDetailDTO);
        return vaccination;
    }

    public void deleteVaccine(Long id) {

        Boolean exists = vaccinationRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        vaccinationRepository.deleteById(id);
    }
}
