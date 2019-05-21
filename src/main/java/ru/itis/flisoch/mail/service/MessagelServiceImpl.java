package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.*;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.exception.LogicalException;
import ru.itis.flisoch.mail.exception.ResourceNotFoundException;
import ru.itis.flisoch.mail.form.MessagesAndAction;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.repository.FolderRepository;
import ru.itis.flisoch.mail.repository.MessageRepository;
import ru.itis.flisoch.mail.repository.MessageUserRepository;
import ru.itis.flisoch.mail.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessagelServiceImpl implements MessagelService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageUserRepository messageUserRepository;
    private final FolderRepository folderRepository;

    @Autowired
    public MessagelServiceImpl(MessageRepository messageRepository, UserRepository userRepository, MessageUserRepository messageUserRepository, FolderRepository folderRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageUserRepository = messageUserRepository;
        this.folderRepository = folderRepository;
    }


    @Override
    @Transactional
    public MessageDto save(NewMailForm form, User sender) {
        Message message = Message.from(form);
        message.setSender(sender);
        message.setSendTime(LocalDateTime.now());
        Long parentId = form.getParentMessageId();
        if (parentId != null) {
            Message parent = messageRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("parent message with id " + parentId + "not found"));
            message.setParentMessage(parent);
        }
        message = messageRepository.save(message);

        addUsersToMessage(message, form);

        return MessageDto.from(message);
    }

    @Override
    @Transactional
    public List<MessageDto> folderMessages(User authUser, String folderName) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Folder folder = user.getFolders().stream()
                .filter(f -> f.getOwner().equals(user) && f.getName().equals(folderName)).findAny()
                .orElseThrow(() -> new ResourceNotFoundException("nout found folder with name " + folderName));
        return folder.getMessages().stream()
                .map(messageUser -> {
                    MessageDto dto = MessageDto.from(messageUser.getMessage());
                    dto.setStatus(messageUser.getStatus());
                    return dto;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void handleMail(User authUser, MessagesAndAction messagesAndAction) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<MessageUser> messageUsers = messageUserRepository
                .findAllByIdAndRecipient(Arrays.asList(messagesAndAction.getMessagesId()), user);

        if (messagesAndAction.getAction().equals(MessageAction.ARCHIVE)) {
            archive(user, messageUsers, messagesAndAction.getFolderFrom());
        }
        else if(messagesAndAction.getAction().equals(MessageAction.MOVETOFOLDER)){
            moveToFolder(user, messageUsers, messagesAndAction.getFolderFrom(), messagesAndAction.getFolderTo());
        }
        else {
            MessageStatus messageStatus = statusByAction(messagesAndAction.getAction());
            setNewStatuses(messageUsers, messageStatus);
        }

    }

    @Transactional
    public void setNewStatuses(List<MessageUser> messageUsers, MessageStatus messageStatus) {
        messageUsers.forEach(messageUser -> messageUser.setStatus(messageStatus));
    }

    @Transactional
    public void moveToFolder(User user, List<MessageUser> messageUsers, String folderFrom, String folderTo) {
        messageUsers.forEach(
                messageUser -> {
                    Folder from = user.getFolders().stream()
                            .filter(folder ->
                                    folder.getName().equals(folderFrom))
                            .findAny()
                            .orElseThrow(() -> new ResourceNotFoundException("folder" + " not found"));
                    from.getMessages().remove(messageUser);

                    Folder to = user.getFolders().stream()
                            .filter(folder ->
                                    folder.getName().equals(folderTo))
                            .findAny()
                            .orElseThrow(() -> new ResourceNotFoundException("folder" + " not found"));
                    to.getMessages().add(messageUser);
                    folderRepository.save(from);
                    folderRepository.save(to);
                }
        );
    }

    @Transactional
    public void archive(User user, List<MessageUser> messageUsers, String folderName) {
        messageUsers.forEach(
                messageUser -> {
                    Folder f = user.getFolders().stream()
                            .filter(folder ->
                                    folder.getName().equals(folderName))
                            .findAny()
                            .orElseThrow(() -> new ResourceNotFoundException("folder" + " not found"));
                    f.getMessages().remove(messageUser);
                    folderRepository.save(f);
                }
        );
    }

    private MessageStatus statusByAction(MessageAction action) {
        switch (action) {
            case DELETE:
                return MessageStatus.DELETED;
            case MARKREAD:
                return MessageStatus.READ;
            case MARKUNREAD:
                return MessageStatus.RECEIVED;
        }
        throw new LogicalException("doesn't support this action" + action.name());
    }

    private void addUsersToMessage(Message message, NewMailForm form) {
        List<User> recipients = usersByUsernames(form.getTo());
        List<User> copyRecipients = usersByUsernames(form.getCc());
        List<User> hiddenCopyRecipients = usersByUsernames(form.getBcc());
        List<MessageUser> messageUsers = new ArrayList<>();
        recipients.forEach(user -> messageUsers.add(userInfoToMessage(user, message, ReceiptType.NORM)));
        copyRecipients.forEach(user -> messageUsers.add(userInfoToMessage(user, message, ReceiptType.CC)));
        hiddenCopyRecipients.forEach(user -> messageUsers.add(userInfoToMessage(user, message, ReceiptType.BCC)));
        message.setMessageUsers(messageUsers);
    }

    private MessageUser userInfoToMessage(User user, Message finalMessage, ReceiptType type) {
        MessageUser messageUser = messageUserRepository.save(
                MessageUser.builder()
                        .recipient(user)
                        .receiptType(type)
                        .status(MessageStatus.RECEIVED)
                        .message(finalMessage).build()
        );
        putMessageToFolders(messageUser);
        return messageUser;
    }

    private void putMessageToFolders(MessageUser messageUser) {
        Folder inbox = folderRepository.findByNameAndOwner(
                DefaultFolderNames.INBOX.name(), messageUser.getRecipient())
                .orElseThrow(() -> new LogicalException("user doesn't have folder " + DefaultFolderNames.INBOX.name()));
        Folder all = folderRepository.findByNameAndOwner(
                DefaultFolderNames.ALL.name(), messageUser.getRecipient())
                .orElseThrow(() -> new LogicalException("user" + messageUser.getRecipient().getUsername() +
                        "doesn't have folder " + DefaultFolderNames.ALL.name()));
        inbox.getMessages().add(messageUser);
        all.getMessages().add(messageUser);
        folderRepository.save(inbox);
        folderRepository.save(all);
    }

    @Transactional
    public List<User> usersByUsernames(List<String> usernames) {
        return usernames.stream()
                .map(username -> userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username)))
                .collect(Collectors.toList());
    }
}
