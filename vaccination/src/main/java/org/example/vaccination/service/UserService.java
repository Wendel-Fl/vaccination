package org.example.vaccination.service;

import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.User;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;
import org.example.vaccination.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        Boolean exists = userRepository.existsById(id);

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

    public User updateUser(UserDetailDTO userDetailDTO) {
        Boolean exists = userRepository.existsById(userDetailDTO.id());

        if (!exists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        User user = userRepository.getReferenceById(userDetailDTO.id());
        user.updateInfo(userDetailDTO);
        return user;
    }

    public void deleteUser(Long id) {

        Boolean exists = userRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException(USER_NOT_FOUND);
        }

        userRepository.deleteById(id);
    }
}
