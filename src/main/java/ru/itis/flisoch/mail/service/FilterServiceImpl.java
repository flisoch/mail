package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterDto;
import ru.itis.flisoch.mail.repository.FilterRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private final UserRepository userRepository;
    private final FilterRepository filterRepository;

    @Autowired
    public FilterServiceImpl(UserRepository userRepository, FilterRepository filterRepository) {
        this.userRepository = userRepository;
        this.filterRepository = filterRepository;
    }

    @Override
    @Transactional
    public List<FilterDto> getFilters(User authUser) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getMyFilters().stream().map(FilterDto::from).collect(Collectors.toList());

    }
}
