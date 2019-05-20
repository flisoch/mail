package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.NewMailForm;

public interface MessagelService {
    MessageDto save(NewMailForm form, User author);
}
