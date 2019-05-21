package ru.itis.flisoch.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.Message;
import ru.itis.flisoch.mail.domain.ReceiptType;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String subject;
    private String text;
    private UserDto sender;
    private long sendTime;
    private List<UserDto> cc;
    private List<UserDto> to;

    public static MessageDto from(Message message) {
        long epoch = message.getSendTime().toInstant(ZoneOffset.UTC).toEpochMilli();
        List<UserDto> cc = new ArrayList<>();
        List<UserDto> to = new ArrayList<>();
        message.getMessageUsers()
                .forEach(messageUser -> {
                            if (messageUser.getReceiptType().equals(ReceiptType.CC)) {
                                cc.add(UserDto.from(messageUser.getRecipient()));
                            } else if (messageUser.getReceiptType().equals(ReceiptType.NORM)) {
                                to.add(UserDto.from(messageUser.getRecipient()));
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
                .build();
    }
}
