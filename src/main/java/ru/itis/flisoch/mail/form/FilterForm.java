package ru.itis.flisoch.mail.form;

import lombok.Data;
import ru.itis.flisoch.mail.domain.MessageAction;

@Data
public class FilterForm {
    private String from;
    private String to;
    private String subject;
    private String containingWords;
    private MessageAction markAs;
    private String moveTo;
    private String copyTo;

}
