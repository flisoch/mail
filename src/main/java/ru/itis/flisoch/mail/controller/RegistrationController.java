package ru.itis.flisoch.mail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.form.RegistrationForm;
import ru.itis.flisoch.mail.service.UserService;

@RestController
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody RegistrationForm form) {
        UserDto user = userService.saveNewUser(form);
        return ResponseEntity.status(201).body(user);
    }


    @GetMapping("/test1")
    public ResponseEntity registration() {
        return ResponseEntity.ok().build();
    }
}
