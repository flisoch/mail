package ru.itis.flisoch.mail.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import ru.itis.flisoch.mail.domain.User;

@Builder
@EqualsAndHashCode(of = "id")
public class UserDto {
    private Long id;
    private String username;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
