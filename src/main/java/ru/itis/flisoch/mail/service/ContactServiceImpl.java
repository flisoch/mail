package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.MyContact;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.form.ContactForm;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final UserRepository userRepository;

    @Autowired
    public ContactServiceImpl( UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserDto> myContactsByNameLike(User authUser, ContactForm form) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<MyContact> contacts = user.getContacts().stream()
                .filter(myContact -> myContact.getMyContact().getUsername().startsWith(form.getUsername()))
                .collect(Collectors.toList());
        return contacts.stream()
                .map(myContact -> UserDto.from(myContact.getMyContact())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<UserDto> getContacts(User authUser) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<UserDto> contacts = user.getContacts().stream()
                .map(myContact -> UserDto.from(myContact.getMyContact())).collect(Collectors.toList());
        return contacts;
    }
}
