package ru.itis.flisoch.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.Filter;
import ru.itis.flisoch.mail.domain.MessageAction;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

    private Long id;
    private UserDto from;
    private UserDto to;
    private String subject;
    private List<String> containingWords;
    private List<MessageAction> messageActions;
    private UserDto owner;

    public static FilterDto from(Filter filter) {
        return FilterDto.builder()
                .id(filter.getId())
                .from(UserDto.from(filter.getFrom()))
                .to(UserDto.from(filter.getTo()))
                .containingWords(filter.getContainingWords())
                .messageActions(filter.getMessageActions())
                .owner(UserDto.from(filter.getOwner()))
                .build();

    }
}
