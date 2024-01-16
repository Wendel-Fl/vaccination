package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vaccination.model.User;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;
import org.example.vaccination.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "user")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDetailDTO>> getAllUsers() {
        List<UserDetailDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDetailDTO> createUser(
            @RequestBody @Valid UserDTO userDTO,
            UriComponentsBuilder uriBuilder
    ) {
        User user = userService.createUser(userDTO);

        var uri = uriBuilder
                .path("user/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new UserDetailDTO(user));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UserDetailDTO> updateUser(
            @RequestBody @Valid UserDetailDTO userDetailDTO
    ) {
        User user = userService.updateUser(userDetailDTO);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @PutMapping(path = "/attach-allergy")
    @Transactional
    public ResponseEntity<UserDetailDTO> attachAllergy(
            @RequestBody @Valid UserDetailDTO userDetailDTO
    ) {
        User user = userService.attachAllergy(userDetailDTO);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @PutMapping(path = "/attach-schedule")
    @Transactional
    public ResponseEntity<UserDetailDTO> attachSchedule(
            @RequestBody @Valid UserDetailDTO userDetailDTO
    ) {
        User user = userService.attachSchedule(userDetailDTO);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
