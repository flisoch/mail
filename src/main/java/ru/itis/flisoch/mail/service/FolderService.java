package ru.itis.flisoch.mail.service;

import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FolderDto;
import ru.itis.flisoch.mail.form.FolderForm;

import java.util.List;

public interface FolderService {
    List<FolderDto> foldersByUser(User user);

    void addFolder(User user, String folderName);

    void deleteFolder(User user, Long folderId);

    FolderDto editFolder(User user, Long folderId, FolderForm form);

    List<FolderDto> customUserFolders(User user);
}
