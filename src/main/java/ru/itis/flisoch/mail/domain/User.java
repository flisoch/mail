package ru.itis.flisoch.mail.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Builder
@Data
@ToString(exclude = {"messages", "messageUsers", "folders", "contacts"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "serviceUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "sender")
    private List<Message> messages;
    private String signature;

    @OneToMany(mappedBy = "recipient")
    private List<MessageUser> messageUsers;

    @OneToMany(mappedBy = "owner")
    private List<Folder> folders;

    @OneToMany(mappedBy = "me")
    private List<MyContact> contacts;

    @OneToMany(mappedBy = "owner")
    private List<Filter> filters;

}

