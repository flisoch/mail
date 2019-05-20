package ru.itis.flisoch.mail.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.flisoch.mail.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
