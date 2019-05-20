package ru.itis.flisoch.mail.security;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.flisoch.mail.domain.User;

public interface MailUserDetails extends UserDetails {
    User getUser();
}
