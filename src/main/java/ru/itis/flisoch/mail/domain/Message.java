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
    @Transient
    private Message parentMessage;

    public static Message from(NewMailForm form) {
        return Message.builder()
                .text(form.getText())
                .subject(form.getSubject())
                .build();
    }
}
