package ru.itis.flisoch.mail.domain;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<User> contacts;
    private List<Message> messages;
    private String dignature;
}
