package ru.itis.flisoch.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
}
