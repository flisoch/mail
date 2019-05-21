package ru.itis.flisoch.mail.form;

import lombok.Data;
import ru.itis.flisoch.mail.domain.MessageAction;

@Data
public class MessagesAndAction {
    private Long[] messagesId;
    private MessageAction action;
    private String folderFrom;
    private String folderTo;
}
