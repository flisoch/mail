package ru.itis.flisoch.mail.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = {"message", "recipient", "folders"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "message_user")
public class MessageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;
    @Enumerated(EnumType.STRING)
    private ReceiptType receiptType;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @ManyToMany(mappedBy = "messages")
    private List<Folder> folders;
}
