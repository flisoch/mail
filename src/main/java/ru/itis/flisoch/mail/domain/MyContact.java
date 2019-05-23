package ru.itis.flisoch.mail.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
public class MyContact {

    @EmbeddedId
    private MyContactId id;

    @ManyToOne
    @JoinColumn(name = "ME_ID", insertable = false, updatable = false)
    private User me;

    @ManyToOne
    @JoinColumn(name = "MY_CONTACT_ID", insertable = false, updatable = false)
    private User myContact;

    @Embeddable
    @Data
    public static class MyContactId implements Serializable {

        @Column(name = "ME_ID", nullable = false, updatable = false)
        private Integer meId;

        @Column(name = "MY_CONTACT_ID", nullable = false, updatable = false)
        private Integer myContactId;

        public boolean equals(Object o) {
            if (o == null)
                return false;

            if (!(o instanceof MyContactId))
                return false;

            MyContactId other = (MyContactId) o;
            if (!(other.getMeId().equals(getMeId())))
                return false;

            return other.getMyContactId().equals(getMyContactId());

        }

        public int hashCode() {
            return Objects.hashCode(this);
        }

    }

}
