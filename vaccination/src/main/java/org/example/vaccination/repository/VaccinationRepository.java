package org.example.vaccination.repository;

import org.example.vaccination.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
