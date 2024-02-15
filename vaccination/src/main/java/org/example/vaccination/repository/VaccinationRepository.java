package org.example.vaccination.repository;

import io.micrometer.observation.ObservationFilter;
import org.example.vaccination.model.Vaccination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    @Query(
            "FROM Vaccination vc " +
            "WHERE (:title IS NULL OR :title = '' OR LOWER(vc.title) LIKE(CONCAT('%', :title, '%'))) " +
            "AND (:description IS NULL OR :description = '' OR LOWER(vc.description) LIKE(CONCAT('%', :description, '%'))) " +
            "AND (:dosage IS NULL OR vc.dosage = :dosage) " +
            "AND (:frequency IS NULL OR vc.frequency = :frequency) " +
            "AND (:interval IS NULL OR vc.interval = :interval) "
    )
    Page<Vaccination> filterVaccination(
            Pageable pageable,
            @Param("title") String title,
            @Param("description") String description,
            @Param("dosage") Integer dosage,
            @Param("frequency") Integer frequency,
            @Param("interval") Integer interval
    );
}
