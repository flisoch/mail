package ru.itis.flisoch.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_id")
    private User from;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private User to;
    private String subject;

    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "filter_word", joinColumns = @JoinColumn(name = "filter_id"))
    @Column(name = "word", nullable = false)
    private List<String> containingWords;

    @ElementCollection(targetClass = MessageAction.class)
    @JoinTable(name = "filter_action", joinColumns = @JoinColumn(name = "filter_id"))
    @Column(name = "action", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<MessageAction> messageActions;



    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
