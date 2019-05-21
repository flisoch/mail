package ru.itis.flisoch.mail.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString(exclude = "owner")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToMany
    @JoinTable(
            name = "message_user_folder",
            joinColumns = @JoinColumn(name = "folder_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<MessageUser> messages;
}
