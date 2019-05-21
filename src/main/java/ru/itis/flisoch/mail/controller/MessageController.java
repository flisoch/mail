package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.MessagelService;

@Controller
@RequestMapping(path = "/mail")
public class MessageController {
    private final MessagelService messagelService;

    @Autowired
    public MessageController(MessagelService messagelService) {
        this.messagelService = messagelService;
    }

    @GetMapping(path = "/inbox")
    public String inbox() {
        return "inbox";
    }

    @GetMapping(path = "/new")
    public String newMail() {
        return "newMail";
    }

    @GetMapping(path = "/sent")
    public String sentMails() {
        return "sent";
    }

    @GetMapping(path = "/all")
    public String allMail() {
        return "archieved";
    }

    @GetMapping(path = "/search-options")
    public String searchOptionsPage() {
        return "searchOptions";
    }

    @PostMapping(path = "/new")
    public String sendMail(Authentication authentication, NewMailForm form, ModelMap modelMap) {
        User sender = ((MailUserDetails) authentication.getPrincipal()).getUser();
        MessageDto mail = messagelService.save(form, sender);
        modelMap.put("mail", mail);
        return "redirect:/mail/" + mail.getId();
    }
}
