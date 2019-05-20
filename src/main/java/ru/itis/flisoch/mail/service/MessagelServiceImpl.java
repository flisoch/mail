package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.Message;
import ru.itis.flisoch.mail.domain.User;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.repository.MessageRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagelServiceImpl implements MessagelService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessagelServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public MessageDto save(NewMailForm form, User sender) {
        Message message = Message.from(form);
        List<User> recipients = usersByUsernames(form.getTo());
        List<User> copyRecipients = usersByUsernames(form.getCc());
        List<User> hiddenCopyRecipients = usersByUsernames(form.getBcc());

        message.setSender(sender);
        message.setRecipients(recipients);
        message.setCopyRecipients(copyRecipients);
        message.setHiddenCopyRecipients(hiddenCopyRecipients);
        message.setSendTime(LocalDateTime.now());
        message = messageRepository.save(message);

        return MessageDto.from(message);
    }

    @Transactional
    public List<User> usersByUsernames(List<String> usernames) {
        return usernames.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username)))
                .collect(Collectors.toList());
    }
}
