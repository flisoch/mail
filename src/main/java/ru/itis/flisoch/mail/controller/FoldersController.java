package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.FolderService;

@Controller
@RequestMapping(path = "/folders")
public class FoldersController {

    private final FolderService folderService;

    @Autowired
    public FoldersController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public String newFolder(Authentication authentication, String folderName) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        folderService.addFolder(user, folderName);
        return "redirect:/mail/INBOX";
    }
}
