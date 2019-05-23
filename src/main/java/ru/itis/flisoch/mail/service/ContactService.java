package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;

import java.util.List;

public interface ContactService {

    List<UserDto> myContactsByNameLike(User authUser, String name);
    List<UserDto> getContacts(User user);

}
