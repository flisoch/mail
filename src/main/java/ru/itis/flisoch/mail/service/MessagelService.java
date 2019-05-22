package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.MessagesAndActions;
import ru.itis.flisoch.mail.form.NewMailForm;

import java.util.List;

public interface MessagelService {
    MessageDto save(NewMailForm form, User author);

    List<MessageDto> folderMessages(User user, String name);

    void handleMail(User user, MessagesAndActions messagesAndActions);

    void deleteMessages(User user, Long[] messagesId);
}
