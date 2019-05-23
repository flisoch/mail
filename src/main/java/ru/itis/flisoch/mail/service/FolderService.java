package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FolderDto;

import java.util.List;

public interface FolderService {
    List<FolderDto> foldersByUser(User user);

    void addFolder(User user, String folderName);
}
