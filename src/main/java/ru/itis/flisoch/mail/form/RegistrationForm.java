package ru.itis.flisoch.mail.form;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String copyPassword;
}
