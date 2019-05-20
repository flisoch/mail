package ru.itis.flisoch.mail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.form.RegistrationForm;
import ru.itis.flisoch.mail.service.UserService;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration(RegistrationForm form, ModelMap modelMap) {
        UserDto user = userService.saveNewUser(form);
        modelMap.put("user", user);
        return "redirect:/auth";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("/auth")
    public String signInPage() {
        return "auth";
    }


}
