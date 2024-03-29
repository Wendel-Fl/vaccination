package org.example.vaccination.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.Status;
import org.hibernate.annotations.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(
            "FROM Schedule sc " +
            "INNER JOIN Users u ON sc.user.id = u.id " +
            "WHERE (:status IS NULL OR sc.status = :status) " +
            "AND ((COALESCE(:initialDate, NULL) IS NULL OR sc.dateTime >= :initialDate )" +
            "AND (COALESCE(:finalDate, NULL) IS NULL OR sc.dateTime <= :finalDate ))" +
            "ORDER BY " +
            "(CASE " +
            "   WHEN sc.status = 'SCHEDULED' THEN 1 " +
            "   WHEN sc.status = 'CARRIED_OUT' THEN 2" +
            "   WHEN sc.status = 'CANCELLED' THEN 3" +
            "   ELSE 4 END) " +
            "ASC"
    )
    Page<Schedule> filterSchedule(
            Pageable pageable,
            @Param("status") Status status,
            @Param("initialDate") LocalDateTime initialDate,
            @Param("finalDate") LocalDateTime finalDate
    );
}
