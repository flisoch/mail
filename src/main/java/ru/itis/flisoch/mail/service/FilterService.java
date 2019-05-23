package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterShortDto;
import ru.itis.flisoch.mail.form.FilterForm;

import java.util.List;

public interface FilterService {

    List<FilterShortDto> getFilters(User user);

    FilterShortDto createFilter(User user, FilterForm filterForm);

    void deleteFilter(User user, Long filterId);
}
