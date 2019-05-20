package ru.itis.flisoch.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.Message;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private String subject;
    private String text;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
        .id(message.getId())
        .subject(message.getSubject())
        .text(message.getText())
        .build();
    }
}
