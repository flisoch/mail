package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.MyContact;
import ru.itis.flisoch.mail.domain.User;

import java.util.List;

public interface ContactRepository extends JpaRepository<MyContact, Long> {
    List<MyContact> findByMeAndMyContact_UsernameLike(User user, String contactName);
}
