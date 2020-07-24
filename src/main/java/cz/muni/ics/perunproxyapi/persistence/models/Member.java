package cz.muni.ics.perunproxyapi.persistence.models;


import cz.muni.ics.perunproxyapi.persistence.enums.MemberStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Member object model.
 *
 * @author Dominik Frantisek Bucik <bucik@.ics.muni.cz>
 * @author Ondrej Ernst <ondra.ernst@gmail.com>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Member {

    @NonNull private Long id;
    @NonNull private Long userId;
    @NonNull private Long voId;
    @NonNull private MemberStatus status;

    public Member(Long id, Long userId, Long voId, MemberStatus status) {
        this.setId(id);
        this.setUserId(userId);
        this.setVoId(voId);
        this.setStatus(status);
    }

    public Member(Long id, Long userId, Long voId, String status) {
        this(id, userId, voId, MemberStatus.fromString(status));
    }

}
