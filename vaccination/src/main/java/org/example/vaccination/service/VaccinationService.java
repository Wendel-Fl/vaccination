package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.exception.dto.DataViolationException;
import org.example.vaccination.model.Vaccination;
import org.example.vaccination.model.dto.VaccinationDTO;
import org.example.vaccination.model.dto.VaccinationDetailDTO;
import org.example.vaccination.repository.VaccinationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private static final String VACCINATION_NOT_FOUND = "Vaccination not found";

    private final VaccinationRepository vaccinationRepository;

    public Vaccination getVaccineById(Long id) {
        boolean exists = vaccinationRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        return vaccinationRepository.getReferenceById(id);
    }

    public Page<VaccinationDetailDTO> getAllVaccines(
            Pageable pageable,
            String title,
            String description,
            Integer dosage,
            Integer frequency,
            Integer interval
    ) {
        return vaccinationRepository
                .filterVaccination(
                        pageable,
                        title,
                        description,
                        dosage,
                        frequency,
                        interval
                )
                .map(VaccinationDetailDTO::new);
    }

    public Vaccination createVaccine(VaccinationDTO vaccinationDTO) {
        Vaccination vaccination = new Vaccination(vaccinationDTO);
        return vaccinationRepository.save(vaccination);
    }

    public Vaccination updateVaccine(VaccinationDetailDTO vaccinationDetailDTO) {
        boolean exists = vaccinationRepository.existsById(vaccinationDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        Vaccination vaccination = vaccinationRepository
                .getReferenceById(vaccinationDetailDTO.id());

        vaccination.updateVaccine(vaccinationDetailDTO);
        return vaccination;
    }

    public void deleteVaccine(Long id) {
        boolean exists = vaccinationRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        Vaccination vaccination = vaccinationRepository.getReferenceById(id);

        if (!vaccination.getSchedules().isEmpty()) {
            throw new DataViolationException("Vacina não pode ser deletada porque está associada à um agendamento");
        }

        vaccinationRepository.deleteById(id);
    }
}
