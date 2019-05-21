package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.MessageUser;

public interface MessageUserRepository extends JpaRepository<MessageUser, Long> {
}
