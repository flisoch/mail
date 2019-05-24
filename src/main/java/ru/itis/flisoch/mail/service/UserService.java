package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.form.RegistrationForm;

public interface UserService {
    UserDto saveNewUser(RegistrationForm form);

    String addSignature(User user, String signature);
}
