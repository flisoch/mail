package ru.itis.flisoch.mail.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.MessageAction;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessagesAndActions {
    private Long[] messagesId;
    private MessageAction[] actions;
    private String folderFrom;
    private String moveTo;
    private String copyTo;
}
