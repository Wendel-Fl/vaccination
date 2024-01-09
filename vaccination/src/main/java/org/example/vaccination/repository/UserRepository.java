package org.example.vaccination.repository;

import org.example.vaccination.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
