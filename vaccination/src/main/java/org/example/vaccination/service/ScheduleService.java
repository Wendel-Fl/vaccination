package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.Status;
import org.example.vaccination.model.User;
import org.example.vaccination.model.Vaccination;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.example.vaccination.repository.ScheduleRepository;
import org.example.vaccination.repository.UserRepository;
import org.example.vaccination.repository.VaccinationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.example.vaccination.model.Status.*;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private static final String USER_NOT_FOUND = "User not found";

    private static final String SCHEDULE_NOT_FOUND = "Schedule not found";

    private static final String VACCINATION_NOT_FOUND = "Vaccination not found";

    private final UserRepository userRepository;

    private final ScheduleRepository scheduleRepository;

    private final VaccinationRepository vaccinationRepository;

    public Schedule getScheduleById(Long id) {
        boolean exists = scheduleRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        return scheduleRepository.getReferenceById(id);
    }

    public List<ScheduleDetailDTO> getAllSchedules(
            Status status, LocalDateTime dateTime
    ) {
        return scheduleRepository
                .filterSchedule(status, dateTime)
                .stream()
                .map(ScheduleDetailDTO::new)
                .toList();
    }

    public List<Schedule> createSchedule(ScheduleDTO scheduleDTO) {
        boolean userExists = userRepository.existsById(scheduleDTO.user().getId());

        boolean vaccinationExists = vaccinationRepository.existsById(scheduleDTO.vaccination().getId());

        if (!userExists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        if (!vaccinationExists) {
            throw new RuntimeException(VACCINATION_NOT_FOUND);
        }

        User user = userRepository.getReferenceById(scheduleDTO.user().getId());

        Vaccination vaccination = vaccinationRepository.getReferenceById(scheduleDTO.vaccination().getId());

        Integer dosage = vaccination.getDosage();
        Integer interval = vaccination.getInterval();
        ChronoUnit frequency = vaccination.getFrequencyUnit();

        List<Schedule> schedules = new ArrayList<>();

        LocalDateTime initialDateTime = scheduleDTO.dateTime();

        for (int i = 0; i < dosage; i++) {
            Schedule schedule = buildSchedule(scheduleDTO, user, vaccination);
            LocalDateTime currentDateTime = initialDateTime.plus((long) i * interval, frequency);
            schedule.setDateTime(currentDateTime);
            schedules.add(schedule);
        }

        return scheduleRepository.saveAll(schedules);
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

    public Schedule scheduleCancelled(ScheduleDetailDTO scheduleDetailDTO) {
        boolean exists = scheduleRepository.existsById(scheduleDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        Schedule schedule = scheduleRepository.getReferenceById(scheduleDetailDTO.id());
        schedule.setStatus(CANCELLED);
        schedule.setStatusDate(LocalDate.now());

        return schedule;
    }

    public Schedule scheduleCarriedOut(ScheduleDetailDTO scheduleDetailDTO) {
        boolean exists = scheduleRepository.existsById(scheduleDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        Schedule schedule = scheduleRepository.getReferenceById(scheduleDetailDTO.id());
        schedule.setStatus(CARRIED_OUT);
        schedule.setStatusDate(LocalDate.now());

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

    private static Schedule buildSchedule(ScheduleDTO scheduleDTO, User user, Vaccination vaccination) {
        Schedule schedule = new Schedule(scheduleDTO);

        schedule.setUser(user);

        schedule.setVaccination(vaccination);

        schedule.setStatus(SCHEDULED);

        schedule.setStatusDate(null);
        return schedule;
    }
}
