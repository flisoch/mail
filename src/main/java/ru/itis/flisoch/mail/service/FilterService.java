package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterDto;

import java.util.List;

public interface FilterService {

    List<FilterDto> getFilters(User user);
}
