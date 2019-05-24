package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.MyContact;

public interface ContactRepository extends JpaRepository<MyContact, Long> {
}
