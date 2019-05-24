package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.DefaultFolderNames;
import ru.itis.flisoch.mail.domain.Folder;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FolderDto;
import ru.itis.flisoch.mail.exception.ResourceNotFoundException;
import ru.itis.flisoch.mail.form.FolderForm;
import ru.itis.flisoch.mail.repository.FolderRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderServiceImpl implements FolderService {

    private final UserRepository userRepository;
    private final FolderRepository folderRepository;

    @Autowired
    public FolderServiceImpl(UserRepository userRepository, FolderRepository folderRepository) {
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
    }

    @Override
    @Transactional
    public List<FolderDto> foldersByUser(User authUser) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user.getFolders().stream().map(FolderDto::from).collect(Collectors.toList());
    }

    @Override
    public void addFolder(User authUser, String folderName) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Folder folder = Folder.builder()
                .name(folderName)
                .owner(user)
                .build();
        folderRepository.save(folder);
    }

    @Override
    @Transactional
    public void deleteFolder(User authUser, Long folderId) {

        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user" + authUser.getUsername() + "not found"));
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("no folder with id " + folderId));
        if (user.getFolders().contains(folder)) {
            folderRepository.delete(folder);
        }
    }

    @Override
    @Transactional
    public FolderDto editFolder(User authUser, Long folderId, FolderForm form) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user" + authUser.getUsername() + "not found"));
        Folder folder = folderRepository.findById(folderId)
                .orElseThrow(() -> new ResourceNotFoundException("no folder with id " + folderId));
        folder.setName(form.getName());
        return FolderDto.from(folderRepository.save(folder));
    }

    @Override
    @Transactional
    public List<FolderDto> customUserFolders(User authUser) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user" + authUser.getUsername() + "not found"));
        List<Folder> folders = user.getFolders();
        List<FolderDto> customFolders = folders.stream().filter(folder -> Arrays.stream(DefaultFolderNames.values())
                .noneMatch((t) -> t.name().equals(folder.getName()))).map(FolderDto::from).collect(Collectors.toList());
        return customFolders;
    }
}
