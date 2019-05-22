package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.flisoch.mail.domain.DefaultFolderNames;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.MessagesAndAction;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.MessagelService;

import java.util.List;

@Controller
@RequestMapping(path = "/mail")
public class MessageController {
    private final MessagelService messagelService;

    @Autowired
    public MessageController(MessagelService messagelService) {
        this.messagelService = messagelService;
    }

    @GetMapping(path = "/inbox")
    public String inbox(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> inboxMessages = messagelService.folderMessages(user, DefaultFolderNames.INBOX.name());
        modelMap.put("messages", inboxMessages);
        return "inbox";
    }

    @GetMapping(path = "/new")
    public String newMail() {
        return "newMail";
    }

    @GetMapping(path = "/sent")
    public String sentMails(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> sentMessages = messagelService.folderMessages(user,DefaultFolderNames.SENT.name());
        return "sent";
    }

    @GetMapping(path = "/all")
    public String allMail(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> allMessages = messagelService.folderMessages(user, DefaultFolderNames.ALL.name());
        modelMap.put("messages", allMessages);
        return "inbox";
    }

    @GetMapping(path = "/search-options")
    public String searchOptionsPage() {
        return "searchOptions";
    }

    @PostMapping(path = "/new")
    public String sendMail(Authentication authentication, ModelMap modelMap, NewMailForm form) {
        User sender = ((MailUserDetails) authentication.getPrincipal()).getUser();
        MessageDto mail = messagelService.save(form, sender);
        modelMap.put("mail", mail);
        return "redirect:/mail/" + mail.getId();
    }

    @PutMapping
    public @ResponseBody ResponseEntity handleMail(Authentication authentication,
                                                   @RequestBody MessagesAndAction messagesAndAction){
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        messagelService.handleMail(user, messagesAndAction);
        return ResponseEntity.status(200).build();
    }
}
