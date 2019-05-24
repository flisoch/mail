package ru.itis.flisoch.mail.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.DefaultFolderNames;
import ru.itis.flisoch.mail.domain.Folder;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.exception.UserAlreadyExistsException;
import ru.itis.flisoch.mail.form.RegistrationForm;
import ru.itis.flisoch.mail.repository.FolderRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FolderRepository folderRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FolderRepository folderRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.folderRepository = folderRepository;
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
        user = userRepository.save(user);
        generateDefaultFolders(user);
        return UserDto.from(user);
    }

    @Override
    @Transactional
    public String addSignature(User user, String signature) {
        user.setSignature(signature);
        user = userRepository.save(user);
        return signature;
    }

    private void generateDefaultFolders(User user) {
        Arrays.stream(DefaultFolderNames.values())
                .forEach(
                        folderName ->
                                folderRepository.save(Folder.builder()
                                        .owner(user)
                                        .name(folderName.name())
                                        .build())
                );
    }
}
