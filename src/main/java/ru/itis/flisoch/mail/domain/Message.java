package ru.itis.flisoch.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.form.NewMailForm;
import ru.itis.flisoch.mail.util.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
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
    @ManyToMany
    @JoinTable(
            name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> recipients;

    @ManyToMany
    @JoinTable(
            name = "message_cc",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> copyRecipients;

    @ManyToMany
    @JoinTable(
            name = "message_bcc",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> hiddenCopyRecipients;
    private String subject;
    private String text;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime sendTime;
    @Transient
    private List<String> attachments;

    /*@ElementCollection
    @JoinTable(name="ATTRIBUTE_VALUE_RANGE", joinColumns=@JoinColumn(name="ID"))
    @MapKeyColumn (name="RANGE_ID")
    @Column(name="VALUE")*/
    @Transient
    private Map<Long, MessageStatus> statusesForUsers;
    @Transient
    private Folder folder;
    @Transient
    private Message parentMessage;

    public static Message from(NewMailForm form) {
        return Message.builder()
                .text(form.getText())
                .subject(form.getSubject())
                .build();
    }
}
