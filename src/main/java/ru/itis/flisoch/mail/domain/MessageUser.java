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
@EqualsAndHashCode(of = {"recipient", "message", "cc", "bcc", "norm"})
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
//    @Enumerated(EnumType.STRING)
    @Transient
    private ReceiptType receiptType;
    private boolean cc;
    private boolean bcc;
    private boolean norm;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @ManyToMany
    @JoinTable(
            name = "message_user_folder",
            joinColumns = @JoinColumn(name = "message_user_id"),
            inverseJoinColumns = @JoinColumn(name = "folder_id")
    )
    private List<Folder> folders;
}
