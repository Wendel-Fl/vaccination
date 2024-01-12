package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.dto.ScheduleDTO;
import org.example.vaccination.model.dto.ScheduleDetailDTO;
import org.example.vaccination.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private static final String SCHEDULE_NOT_FOUND = "Schedule not found";

    private final ScheduleRepository scheduleRepository;

    public Schedule getScheduleById(Long id) {
        Boolean exists = scheduleRepository.existsById(id);

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
        Boolean exists = scheduleRepository.existsById(scheduleDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        Schedule schedule = scheduleRepository.getReferenceById(scheduleDetailDTO.id());
        schedule.updateInfo(scheduleDetailDTO);
        return schedule;
    }

    public void deleteSchedule(Long id) {
        Boolean exists = scheduleRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        scheduleRepository.deleteById(id);
    }
}
