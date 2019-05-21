package ru.itis.flisoch.mail.domain;

import lombok.*;
import ru.itis.flisoch.mail.form.RegistrationForm;

import javax.persistence.*;
import java.util.List;

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
    @Transient
    private List<User> contacts;
    @OneToMany(mappedBy = "sender")
    private List<Message> messages;
    private String signature;

    @OneToMany(mappedBy = "recipient")
    private List<MessageUser> messageUsers;

    @OneToMany(mappedBy = "owner")
    private List<Folder> folders;
}
