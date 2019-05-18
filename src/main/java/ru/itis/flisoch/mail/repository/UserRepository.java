package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
