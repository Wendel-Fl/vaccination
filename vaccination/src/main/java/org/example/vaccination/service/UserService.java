package org.example.vaccination.service;

import lombok.AllArgsConstructor;
import org.example.vaccination.model.User;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;
import org.example.vaccination.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepository.save(user);
    }

    public User updateUser(UserDetailDTO userDetailDTO) {
        User user = userRepository.getReferenceById(userDetailDTO.id());
        user.updateInfo(userDetailDTO);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
