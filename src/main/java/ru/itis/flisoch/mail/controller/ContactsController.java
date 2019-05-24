package ru.itis.flisoch.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.form.ContactForm;
import ru.itis.flisoch.mail.security.MailUserDetails;
import ru.itis.flisoch.mail.service.ContactService;

import java.util.List;


@RestController
@RequestMapping(path = "/contacts")
public class ContactsController {

    private final ContactService contactService;

    @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity newContact(Authentication authentication) {
        return null;
    }

    @GetMapping
    public ResponseEntity myContacts(Authentication authentication) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<UserDto> contacts = contactService.getContacts(user);
        return ResponseEntity.ok(contacts);
    }

    @PostMapping("/like")
    public ResponseEntity contactsByNameLike(Authentication authentication, @RequestBody ContactForm form) {
        User user = ((MailUserDetails) authentication.getPrincipal()).getUser();
        List<UserDto> contacts = contactService.myContactsByNameLike(user, form);
        return ResponseEntity.ok(contacts);
    }
}
