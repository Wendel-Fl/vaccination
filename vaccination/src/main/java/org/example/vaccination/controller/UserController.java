package org.example.vaccination.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.vaccination.model.User;
import org.example.vaccination.model.dto.UserDTO;
import org.example.vaccination.model.dto.UserDetailDTO;
import org.example.vaccination.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDetailDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(path = "/register")
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

    @PutMapping(path = "/update")
    @Transactional
    public ResponseEntity<UserDetailDTO> updateUser(UserDetailDTO userDetailDTO) {
        User user = userService.updateUser(userDetailDTO);
        return ResponseEntity.ok(new UserDetailDTO(user));
    }

    @DeleteMapping(path = "/delete/{id}")
    @Transactional
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
