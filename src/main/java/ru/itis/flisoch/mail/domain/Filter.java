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
@Entity
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromUser;
    private String toUser;
    private String subject;
    private String containingWords;

    @ManyToOne
    @JoinColumn(name = "move_folder_id")
    private Folder moveTo;
    @ManyToOne
    @JoinColumn(name = "copy_folder_id")
    private Folder copyTo;
    @Enumerated(EnumType.STRING)
    private MessageAction markAs;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
