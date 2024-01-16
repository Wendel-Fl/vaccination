package org.example.vaccination.repository;

import org.example.vaccination.model.Schedule;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("FROM Schedule ")
    List<Schedule> filterSchedule();
}
