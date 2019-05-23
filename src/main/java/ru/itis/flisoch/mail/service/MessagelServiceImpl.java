package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.*;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.exception.LogicalException;
import ru.itis.flisoch.mail.exception.ResourceNotFoundException;
import ru.itis.flisoch.mail.form.MessagesAndActions;
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
    public MessageDto save(NewMailForm form, User authSender) {
        User sender = userRepository.findByUsername(authSender.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user with name"
                        + authSender.getUsername() + " not found"));
        Message message = Message.from(form);
        message.setSender(sender);
        message.setSendTime(LocalDateTime.now());
        Long parentId = form.getParentMessageId();
        if (parentId != null) {
            Message parent = messageRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("parent message with id " + parentId + "not found"));
            message.setParentMessage(parent);
        }
        if (sender.getSignature() != null) {
            message.setText(message.getText() + "\n\n" + sender.getSignature());
        }
        message = messageRepository.save(message);
        addUsersToMessage(message, form);
        addSenderToMessageUser(message, sender); //you can combine it with prev method

        return MessageDto.from(message);
    }

    @Transactional
    public void addSenderToMessageUser(Message message, User sender) {
        MessageUser messageUser = MessageUser.builder()
                .message(message)
                .recipient(sender)
                .status(MessageStatus.SENT)
                .build();
        messageUser = messageUserRepository.save(messageUser);
        addToSentFolder(sender, messageUser);
    }

    @Transactional
    public void addToSentFolder(User sender, MessageUser messageUser) {
        Folder sent = sender.getFolders().stream().filter(folder -> folder.getName().equals(DefaultFolderNames.SENT.name()))
                .findAny().orElseThrow(() -> new ResourceNotFoundException("folder SENT not found"));
        sent.getMessages().add(messageUser);
        folderRepository.save(sent);
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
                    if (!dto.getStatus().equals(MessageStatus.SENT)) {
                        dto.setBcc(new ArrayList<>());
                    }
                    return dto;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void handleMail(User authUser, MessagesAndActions messagesAndActions) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<MessageUser> messageUsers = messageUserRepository
                .findByRecipientAndMessage_IdIn(user, Arrays.asList(messagesAndActions.getMessagesId()));

        Arrays.asList(messagesAndActions.getActions()).forEach(
                action -> {
                    if (action.equals(MessageAction.MARKREAD) || action.equals(MessageAction.MARKUNREAD)
                            || action.equals(MessageAction.DELETE)) {
                        MessageStatus messageStatus = statusByAction(action);
                        setNewStatuses(messageUsers, messageStatus);
                    }
                    if (action.equals(MessageAction.MOVETOFOLDER)) {
                        moveToFolder(user, messageUsers, messagesAndActions.getFolderFrom(), messagesAndActions.getMoveTo());
                    }
                    if (action.equals(MessageAction.COPYTOFOLDER)) {
                        copyToFolder(user, messageUsers, messagesAndActions.getFolderFrom(), messagesAndActions.getCopyTo());

                    }
                }
        );
    }

    @Override
    @Transactional
    public void deleteMessages(User authUser, Long[] messagesId) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<MessageUser> messageUsers = messageUserRepository
                .findByRecipientAndMessage_IdIn(user, Arrays.asList(messagesId));
        messageUsers.forEach(
                messageUser -> {
                    messageUser.getFolders().stream()
                            .filter(folder ->
                                    folder.getName().equals(DefaultFolderNames.BIN.name()))
                            .findAny()
                            .ifPresent(folder -> folder.getMessages().remove(messageUser));

                });

    }

    @Transactional
    public void copyToFolder(User user, List<MessageUser> messageUsers, String folderFrom, String copyTo) {
        if (folderFrom.equals(copyTo)) {
            return;
        }

        messageUsers.forEach(
                messageUser -> {
                    if (messageUser.getStatus().equals(MessageStatus.SENT)) {
                        return;
                    }
                    Folder to = user.getFolders().stream()
                            .filter(folder ->
                                    folder.getName().equals(copyTo))
                            .findAny()
                            .orElseThrow(() -> new ResourceNotFoundException("folder" + " not found"));
                    to.getMessages().add(messageUser);
                    folderRepository.save(to);
                }
        );
    }

    @Transactional
    public void moveToFolder(User user, List<MessageUser> messageUsers, String folderFrom, String folderTo) {
        if (folderFrom.equals(folderTo)) {
            return;
        }
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
    public void setNewStatuses(List<MessageUser> messageUsers, MessageStatus messageStatus) {
        messageUsers.forEach(messageUser -> {
            if (messageUser.getStatus().equals(MessageStatus.SENT) && !messageStatus.equals(MessageStatus.DELETED)) {
                return;
            }
            messageUser.setStatus(messageStatus);
            messageUserRepository.save(messageUser);
        });
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
        messageUsers.stream()
                .peek(messageUser -> setOtherReceiptTypes(messageUser, messageUsers)) //yoy can set them on prev step
                .distinct()
                .forEach(mUser -> {
                            mUser = messageUserRepository.save(mUser);
                            putMessageToFolders(mUser);
                        }
                );

        message.setMessageUsers(messageUsers);
    }

    private void setOtherReceiptTypes(MessageUser messageUser, List<MessageUser> messageUsers) {
        messageUsers.forEach(m -> {
                    if (m.getRecipient().equals(messageUser.getRecipient())) {
                        messageUser.setCc(messageUser.isCc() || m.isCc());
                        messageUser.setBcc(messageUser.isBcc() || m.isBcc());
                        messageUser.setNorm(messageUser.isNorm() || m.isNorm());
                    }
                }
        );
    }

    private MessageUser userInfoToMessage(User user, Message message, ReceiptType type) {
        MessageUser messageUser = MessageUser.builder()
                .recipient(user)
                .status(MessageStatus.RECEIVED)
                .message(message)
                .build();

        switch (type) {
            case CC:
                messageUser.setCc(true);
                break;
            case NORM:
                messageUser.setNorm(true);
                break;
            case BCC:
                messageUser.setBcc(true);
                break;
        }
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
