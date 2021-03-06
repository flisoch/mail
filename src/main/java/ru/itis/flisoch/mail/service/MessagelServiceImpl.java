package ru.itis.flisoch.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.flisoch.mail.domain.*;
import ru.itis.flisoch.mail.dto.MessageDto;
import ru.itis.flisoch.mail.dto.UserDto;
import ru.itis.flisoch.mail.exception.LogicalException;
import ru.itis.flisoch.mail.exception.ResourceNotFoundException;
import ru.itis.flisoch.mail.form.MessagesAndActions;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class MessagelServiceImpl implements MessagelService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageUserRepository messageUserRepository;
    private final FolderRepository folderRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public MessagelServiceImpl(MessageRepository messageRepository, UserRepository userRepository, MessageUserRepository messageUserRepository, FolderRepository folderRepository, ContactRepository contactRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageUserRepository = messageUserRepository;
        this.folderRepository = folderRepository;
        this.contactRepository = contactRepository;
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
        List<MessageUser> users = addUsersToMessage(message, form);
        sender = addUsersToSenderContacts(users, sender);
        addSenderToMessageUser(message, sender); //you can combine it with prev method
        return MessageDto.from(message);
    }


    private User addUsersToSenderContacts(List<MessageUser> mUsers, User sender) {
        List<MyContact> contacts = sender.getContacts();
        mUsers.forEach(mUser -> {
            AtomicBoolean inContacts = new AtomicBoolean(false);
            contacts.forEach(contact -> {
                if (contact.getMyContact().equals(mUser.getRecipient())) {
                    inContacts.set(true);
                }
            });
            if (!inContacts.get()) {
                MyContact myContact = new MyContact();
                myContact.setMe(sender);
                myContact.setMyContact(mUser.getRecipient());
                myContact = contactRepository.save(myContact);
                contacts.add(myContact);
            }
        });
        sender.setContacts(contacts);
        return userRepository.save(sender);
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
                .findByRecipientAndMessage_IdIn(user, Arrays.asList(messagesAndActions.getMessagesId()))
                .stream().filter(messageUser -> {
                    if (messagesAndActions.getFolderFrom().equals(DefaultFolderNames.SENT.name())) {
                        return messageUser.getStatus().equals(MessageStatus.SENT);
                    }
                    return true;
                }).collect(Collectors.toList());
        ;
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

    @Override
    @Transactional
    public List<MessageDto> getAllByRecipient(User authUser, Long messageId) {
        User user = userRepository.findByUsername(authUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        List<MessageUser> messageUsers = messageUserRepository
                .findByRecipientAndMessage_Id(user, messageId);
        return messageUsers.stream().filter(messageUser -> !messageUser.getStatus().equals(MessageStatus.SENT))
                .peek(messageUser -> {
                    messageUser.setStatus(MessageStatus.READ);
                    messageUserRepository.save(messageUser);
                })
                .map(this::dtoFromMessageUser).collect(Collectors.toList());

    }

    private MessageDto dtoFromMessageUser(MessageUser messageUser) {
        Message message = messageUser.getMessage();
        MessageDto messageDto = MessageDto.from(message);

        List<UserDto> cc = new ArrayList<>();
        List<UserDto> to = new ArrayList<>();
        List<UserDto> bcc = new ArrayList<>();
        message.getMessageUsers()
                .forEach(mUser -> {
                            if (mUser.isCc()) {
                                cc.add(UserDto.from(mUser.getRecipient()));
                            } else if (mUser.isNorm()) {
                                to.add(UserDto.from(mUser.getRecipient()));
                            } else if (mUser.isBcc()) {
                                bcc.add(UserDto.from(mUser.getRecipient()));
                            }
                        }
                );
        messageDto.setTo(to);
        messageDto.setCc(cc);
        messageDto.setBcc(bcc);
        if (message.getParentMessage() != null) {
            messageDto.setParent(findParent(messageUser));
        }
        return messageDto;
    }

    @Transactional
    public MessageDto findParent(MessageUser messageUser) {
        Message message = messageUser.getMessage();
        User sender = message.getSender();
        Message parentMessage = message.getParentMessage();

        MessageUser messageUser2 = parentMessage.getMessageUsers().stream()
                .filter(messageUser1 ->  messageUser1.getRecipient().equals(sender)
                        && messageUser1.getMessage().equals(message.getParentMessage()))

                .findAny().orElseThrow(() -> new LogicalException("blabla"));
        return dtoFromMessageUser(messageUser2);
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
                    if (to.getName().equals(DefaultFolderNames.SENT.name())) {
                        if (!messageUser.getStatus().equals(MessageStatus.SENT)) {
                            return;
                        }
                    }
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

    private List<MessageUser> addUsersToMessage(Message message, NewMailForm form) {
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
                            filter(mUser.getRecipient().getFilters(), mUser);
                        }
                );

        message.setMessageUsers(messageUsers);
        return messageUsers;
    }

    @Transactional
    public void filter(List<Filter> filters, MessageUser mUser) {
        filters.forEach(filter -> {
            doFilter(mUser, filter);
        });
    }

    @Transactional
    public void doFilter(MessageUser mUser, Filter filter) {
        String text = mUser.getMessage().getText();
        String subject = mUser.getMessage().getSubject();
        User sender = mUser.getMessage().getSender();
        User recipient = mUser.getRecipient();

        boolean needFiltration = true;
        if (filter.getFromUser() != null && !filter.getFromUser().equals(sender.getUsername())) {
            needFiltration = false;
        }
        if (filter.getToUser() != null && !filter.getToUser().equals(sender.getUsername())) {
            needFiltration = false;
        }
        if (filter.getSubject() != null && !subject.contains(filter.getSubject())) {
            needFiltration = false;
        }
        if (filter.getContainingWords() != null && !text.contains(filter.getContainingWords())) {
            needFiltration = false;
        }
        if (needFiltration) {

            if (filter.getMoveTo() != null) {
                Folder inbox = folderRepository.findByNameAndOwner(
                        DefaultFolderNames.INBOX.name(), mUser.getRecipient())
                        .orElseThrow(() -> new LogicalException("user doesn't have folder " + DefaultFolderNames.INBOX.name()));
                Folder all = folderRepository.findByNameAndOwner(
                        DefaultFolderNames.ALL.name(), mUser.getRecipient())
                        .orElseThrow(() -> new LogicalException("user" + mUser.getRecipient().getUsername() +
                                "doesn't have folder " + DefaultFolderNames.ALL.name()));
                Folder moveTo = filter.getMoveTo();
                if (!moveTo.getMessages().contains(mUser)) {
                    inbox.getMessages().remove(mUser);
                    all.getMessages().remove(mUser);
                    inbox = folderRepository.save(inbox);
                    all = folderRepository.save(all);
                    moveTo.getMessages().add(mUser);
                    folderRepository.save(moveTo);
                }
            }
            if (filter.getCopyTo() != null) {
                Folder copyTo = filter.getCopyTo();
                if (!copyTo.getMessages().contains(mUser)) {
                    copyTo.getMessages().add(mUser);
                    folderRepository.save(copyTo);
                }

            }
            if (filter.getMarkAs() != null) {
                if (filter.getMarkAs().equals(MessageAction.MARKREAD)
                        && !mUser.getStatus().equals(MessageStatus.READ)) {
                    mUser.setStatus(MessageStatus.READ);
                } else if (filter.getMarkAs().equals(MessageAction.MARKUNREAD)
                        && !mUser.getStatus().equals(MessageStatus.RECEIVED)) {
                    mUser.setStatus(MessageStatus.RECEIVED);
                }
            }
            messageUserRepository.save(mUser);
        }
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
