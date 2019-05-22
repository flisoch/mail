package ru.itis.flisoch.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.Folder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolderDto {
    private Long id;
    private String name;

    public static FolderDto from(Folder folder) {
        return FolderDto.builder()
                .id(folder.getId())
                .name(folder.getName())
                .build();
    }
}
