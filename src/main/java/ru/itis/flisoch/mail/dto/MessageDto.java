package ru.itis.flisoch.mail.dto;

import lombok.*;
import ru.itis.flisoch.mail.domain.Message;
import ru.itis.flisoch.mail.domain.MessageStatus;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "sender"})
public class MessageDto {
    private Long id;
    private String subject;
    private String text;
    private UserDto sender;
    private long sendTime;
    private List<UserDto> cc;
    private List<UserDto> to;
    private List<UserDto> bcc;
    private MessageStatus status;

    public static MessageDto from(Message message) {
        long epoch = message.getSendTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        List<UserDto> cc = new ArrayList<>();
        List<UserDto> to = new ArrayList<>();
        List<UserDto> bcc = new ArrayList<>();
        message.getMessageUsers()
                .forEach(messageUser -> {
                            if (messageUser.isCc()) {
                                cc.add(UserDto.from(messageUser.getRecipient()));
                            } else if (messageUser.isNorm()) {
                                to.add(UserDto.from(messageUser.getRecipient()));
                            } else if (messageUser.isBcc()) {
                                bcc.add(UserDto.from(messageUser.getRecipient()));
                            }
                        }
                );
        return MessageDto.builder()
                .id(message.getId())
                .subject(message.getSubject())
                .text(message.getText())
                .sender(UserDto.from(message.getSender()))
                .sendTime(epoch)
                .cc(cc)
                .to(to)
                .bcc(bcc)
                .build();
    }
}
