package ru.itis.flisoch.mail.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.exception.UserAlreadyExistsException;
import ru.itis.flisoch.mail.form.RegistrationForm;
import ru.itis.flisoch.mail.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveNewUser(RegistrationForm form) {
        if (userRepository.findByUsername(form.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("user with such username already exists");
        }
        User user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        return UserDto.from(userRepository.save(user));
    }
}
