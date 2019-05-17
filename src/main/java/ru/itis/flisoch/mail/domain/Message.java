package ru.itis.flisoch.mail.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Message {
    private User sender;
    private List<User> recipients;
    private List<User> copyRecipients;
    private List<User> hiddenCopyRecipients;
    private String subject;
    private String content;
    private LocalDateTime sendTime;
    private List<String> attachments;


    private Folder folder;
    private Message parentMessage;

}
