package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.example.vaccination.repository.ScheduleRepository;
import org.example.vaccination.repository.VaccinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private static final String SCHEDULE_NOT_FOUND = "Schedule not found";

    private static final String VACCINATION_NOT_FOUND = "Vaccination not found";

    private final ScheduleRepository scheduleRepository;

    private final VaccinationRepository vaccinationRepository;

    public Schedule getScheduleById(Long id) {
        boolean exists = scheduleRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        return scheduleRepository.getReferenceById(id);
    }

    public List<ScheduleDetailDTO> getAllSchedules() {
        return scheduleRepository
                .findAll()
                .stream()
                .map(ScheduleDetailDTO::new)
                .toList();
    }

    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule(scheduleDTO);
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(ScheduleDetailDTO scheduleDetailDTO) {
        boolean exists = scheduleRepository.existsById(scheduleDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        Schedule schedule = scheduleRepository.getReferenceById(scheduleDetailDTO.id());
        schedule.updateInfo(scheduleDetailDTO);
        return schedule;
    }

    public Schedule attachVaccination(ScheduleDetailDTO scheduleDetailDTO) {
        boolean scheduleExists = scheduleRepository.existsById(scheduleDetailDTO.id());
        boolean vaccinationExists = vaccinationRepository
                .existsById(scheduleDetailDTO.vaccination().getId());

        if (!scheduleExists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        if (!vaccinationExists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        Schedule schedule = scheduleRepository.getReferenceById(scheduleDetailDTO.id());

//        schedule.attachVaccination(scheduleDetailDTO.vaccination());

        schedule.setVaccination(scheduleDetailDTO.vaccination());

        return schedule;
    }

    public void deleteSchedule(Long id) {
        boolean exists = scheduleRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        scheduleRepository.deleteById(id);
    }
}
