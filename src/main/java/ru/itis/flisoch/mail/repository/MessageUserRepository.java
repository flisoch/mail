package ru.itis.flisoch.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.flisoch.mail.domain.MessageUser;
import ru.itis.flisoch.mail.domain.User;

import java.util.List;

public interface MessageUserRepository extends JpaRepository<MessageUser, Long> {
    List<MessageUser> findAllByRecipientAndId(User user, Long id);

    List<MessageUser> findAllByIdAndRecipient(Iterable<Long> ids, User user);
}
