package ru.itis.flisoch.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectFromRoot() {
        return "redirect:/mail/inbox";
    }
}
