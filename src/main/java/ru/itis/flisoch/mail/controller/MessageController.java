package ru.itis.flisoch.mail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/mail")
public class MessageController {
    @GetMapping(path = "/inbox")
    public String inbox(){
        return "inbox";
    }

    @GetMapping(path = "/new")
    public String newMail(){
        return "newMail";
    }

    @GetMapping(path = "/sent")
    public String sentMails(){
        return "sent";
    }

    @GetMapping(path = "/all")
    public String allMail(){
        return "archieved";
    }
}
