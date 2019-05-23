package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.Filter;
import ru.itis.flisoch.mail.domain.Folder;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FilterShortDto;
import ru.itis.flisoch.mail.exception.ResourceNotFoundException;
import ru.itis.flisoch.mail.form.FilterForm;
import ru.itis.flisoch.mail.repository.FilterRepository;
import ru.itis.flisoch.mail.repository.FolderRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilterServiceImpl implements FilterService {

    private final UserRepository userRepository;
    private final FilterRepository filterRepository;
    private final FolderRepository folderRepository;

    @Autowired
    public FilterServiceImpl(UserRepository userRepository, FilterRepository filterRepository, FolderRepository folderRepository) {
        this.userRepository = userRepository;
        this.filterRepository = filterRepository;
        this.folderRepository = folderRepository;
    }

    @Override
    @Transactional
    public List<FilterShortDto> getFilters(User authUser) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getMyFilters().stream().map(FilterShortDto::from).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public FilterShortDto createFilter(User authUser, FilterForm filterForm) {
        Filter filter = new Filter();
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user" + authUser.getUsername() + "not found"));
        filter.setOwner(user);

        if (filterForm.getFrom() != null && !filterForm.getFrom().equals("")) {
            filter.setFromUser(filterForm.getFrom());
        }
        if (filterForm.getTo() != null && !filterForm.getTo().equals("")) {
            filter.setToUser(filterForm.getTo());
        }
        if (filterForm.getContainingWords() != null && !filterForm.getContainingWords().equals("")) {
            filter.setContainingWords(filterForm.getContainingWords());
        }
        if (filterForm.getSubject() != null && !filterForm.getSubject().equals("")) {
            filter.setSubject(filterForm.getSubject());
        }
        if (filterForm.getMarkAs() != null && !filterForm.getMarkAs().name().equals("")) {
            filter.setMarkAs(filterForm.getMarkAs());
        }
        if (filterForm.getMoveTo() != null && !filterForm.getMoveTo().equals("")) {
            Folder moveTo = folderRepository.findByNameAndOwner(filterForm.getMoveTo(), user)
                    .orElseThrow(() -> new ResourceNotFoundException("folder " + filterForm.getMoveTo() + "not found"));
            filter.setMoveTo(moveTo);
        }
        if (filterForm.getCopyTo() != null && !filterForm.getCopyTo().equals("")) {
            Folder copyTo = folderRepository.findByNameAndOwner(filterForm.getCopyTo(), user)
                    .orElseThrow(() -> new ResourceNotFoundException("folder " + filterForm.getCopyTo() + "not found"));
            filter.setMoveTo(copyTo);
        }
        filter = filterRepository.save(filter);

        return FilterShortDto.from(filter);
    }
}
