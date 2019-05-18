package ru.itis.flisoch.mail.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.util.Collection;

public class MailUserDetailsImpl implements MailUserDetails {
    public MailUserDetailsImpl(User user) {
        this.user = user;
    }

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
