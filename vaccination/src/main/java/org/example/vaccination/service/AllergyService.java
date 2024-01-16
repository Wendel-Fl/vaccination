package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.dto.AllergyDTO;
import org.example.vaccination.model.dto.AllergyDetailDTO;
import org.example.vaccination.repository.AllergyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergyService {

    private static final String ALLERGY_NOT_FOUND = "Allergy not found";

    private final AllergyRepository allergyRepository;

    public Allergy getAllergyById(Long id) {
        boolean exists = allergyRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(ALLERGY_NOT_FOUND);
        }

        return allergyRepository.getReferenceById(id);
    }

    public List<AllergyDetailDTO> getAllAllergies() {
        return allergyRepository
                .findAll()
                .stream()
                .map(AllergyDetailDTO::new)
                .toList();
    }

    public Allergy createAllergy(AllergyDTO allergyDTO) {
        Allergy allergy = new Allergy(allergyDTO);
        return allergyRepository.save(allergy);
    }

    public Allergy updateAllergy(AllergyDetailDTO allergyDetailDTO) {
        boolean exists = allergyRepository.existsById(allergyDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(ALLERGY_NOT_FOUND);
        }

        Allergy allergy = allergyRepository.getReferenceById(allergyDetailDTO.id());
        allergy.updateInfo(allergyDetailDTO);
        return allergy;
    }

    public void deleteAllergy(Long id) {

        boolean exists = allergyRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(ALLERGY_NOT_FOUND);
        }

        allergyRepository.deleteById(id);
    }
}
