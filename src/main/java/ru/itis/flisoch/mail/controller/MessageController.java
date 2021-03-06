package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.flisoch.mail.domain.DefaultFolderNames;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.FolderDto;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.MessagesAndActions;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.FolderService;
import ru.itis.flisoch.mail.service.MessagelService;

import java.util.List;

@Controller
@RequestMapping(path = "/mail")
public class MessageController {
    private final MessagelService messagelService;
    private final FolderService folderService;

    @Autowired
    public MessageController(MessagelService messagelService, FolderService folderService) {
        this.messagelService = messagelService;
        this.folderService = folderService;
    }

    @GetMapping(path = "/{folderName}")
    public String inbox(Authentication authentication, ModelMap modelMap, @PathVariable String folderName) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> inboxMessages = messagelService.folderMessages(user, folderName);
        modelMap.put("messages", inboxMessages);
        modelMap.put("currentFolder", folderName);
        return "inbox-template";
    }

    @GetMapping(path = "/new")
    public String newMail(Authentication authentication, ModelMap modelMap, String qwe) {
        return "newMail";
    }

    @GetMapping(path = "/sent")
    public String sentMails(Authentication authentication, ModelMap modelMap) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> sentMessages = messagelService.folderMessages(user, DefaultFolderNames.SENT.name());
        modelMap.put("messages", sentMessages);
        return "sent";
    }
    @GetMapping(path = "/messages/{messageId}")
    public String getMessage(Authentication authentication, ModelMap modelMap, @PathVariable Long messageId) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> sentMessages = messagelService.getAllByRecipient(user, messageId);
        modelMap.put("messages", sentMessages);
        return "messages";
    }

    @GetMapping(path = "/messages/{messageId}/reply")
    public String reply(Authentication authentication, ModelMap modelMap, @PathVariable Long messageId) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<MessageDto> sentMessages = messagelService.getAllByRecipient(user, messageId);
        modelMap.put("messages", sentMessages);
        return "reply";
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
        return "redirect:/mail/messages/" + mail.getId();
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity handleMail(Authentication authentication,
                              @RequestBody MessagesAndActions messagesAndActions) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        messagelService.handleMail(user, messagesAndActions);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deleteMail(Authentication authentication,
                              @RequestBody MessagesAndActions messagesAndActions) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        messagelService.deleteMessages(user, messagesAndActions.getMessagesId());
        return ResponseEntity.status(200).build();
    }
}
