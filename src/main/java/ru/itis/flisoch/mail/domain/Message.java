package ru.itis.flisoch.mail.domain;

import lombok.*;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(exclude = {"sender", "messageUsers", "parentMessage"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    private String subject;
    private String text;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime sendTime;
    @Transient
    private List<String> attachments;

    @OneToMany(mappedBy = "message")
    private List<MessageUser> messageUsers;
    @Transient
    private Folder folder;

    @ManyToOne
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage;

    @OneToMany(mappedBy = "parentMessage")
    private List<Message> childrenMessages;

    public static Message from(NewMailForm form) {
        return Message.builder()
                .text(form.getText())
                .subject(form.getSubject())
                .build();
    }
}
