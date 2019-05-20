package ru.itis.flisoch.mail.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewMailForm {
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String text;
    

}
