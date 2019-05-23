package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.MyContact;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.repository.ContactRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> myContactsByNameLike(User authUser, String name) {
        List<MyContact> contacts = contactRepository.findByMeAndMyContact_UsernameLike(authUser, name);
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
