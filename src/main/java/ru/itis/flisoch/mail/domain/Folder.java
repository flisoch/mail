package ru.itis.flisoch.mail.domain;

import java.util.List;

public class Folder {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;
}
