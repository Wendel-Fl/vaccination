package org.example.vaccination.repository;

import org.example.vaccination.model.Allergy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    @Query(
            "FROM Allergy al " +
            "WHERE (:name IS NULL OR LOWER(al.name) LIKE LOWER(CONCAT('%', :name, '%'))) "
    )
    Page<Allergy> filterAllergy(
            Pageable pageable,
            @Param("name") String name
    );
}
