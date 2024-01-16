package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.Allergy;
import org.example.vaccination.model.Schedule;
import org.example.vaccination.model.User;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;
import org.example.vaccination.repository.AllergyRepository;
import org.example.vaccination.repository.ScheduleRepository;
import org.example.vaccination.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "User not found";

    private static final String ALLERGY_NOT_FOUND = "Allergy not found";

    private static final String SCHEDULE_NOT_FOUND = "Schedule not found";

    private final UserRepository userRepository;
    
    private final AllergyRepository allergyRepository;
    
    private final ScheduleRepository scheduleRepository;

    public User getUserById(Long id) {
        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        return userRepository.getReferenceById(id);
    }

    public List<UserDetailDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserDetailDTO::new)
                .toList();
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }
    
    public User attachAllergy(UserDetailDTO userDetailDTO) {
        boolean userExists = userRepository.existsById(userDetailDTO.id());
        boolean allergyExists = allergyRepository
                .existsById(
                        userDetailDTO
                                .allergies()
                                .stream()
                                .map(Allergy::getId)
                                .count()
                );

        if (!userExists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        if (!allergyExists) {
            throw new RuntimeException(ALLERGY_NOT_FOUND);
        }

        User user = userRepository.getReferenceById(userDetailDTO.id());

        user.setAllergies(userDetailDTO.allergies());

//        user.attachAllergy(userDetailDTO);

        return user;
    }

    public User attachSchedule(UserDetailDTO userDetailDTO) {
        boolean userExists = userRepository.existsById(userDetailDTO.id());
        boolean scheduleExists = scheduleRepository
                .existsById(
                        userDetailDTO
                                .schedules()
                                .stream()
                                .map(Schedule::getId)
                                .count()
                );

        if (!userExists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        if (!scheduleExists) {
            throw new RuntimeException(SCHEDULE_NOT_FOUND);
        }

        User user = userRepository.getReferenceById(userDetailDTO.id());

        user.setSchedules(userDetailDTO.schedules());

        return user;
    }

    public User updateUser(UserDetailDTO userDetailDTO) {
        boolean exists = userRepository.existsById(userDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        User user = userRepository.getReferenceById(userDetailDTO.id());
        user.updateInfo(userDetailDTO);
        return user;
    }

    public void deleteUser(Long id) {

        boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
