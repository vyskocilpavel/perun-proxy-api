package cz.muni.ics.perunproxyapi.persistence.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Virtual Organization (Vo) object model.
 *
 * @author Dominik Frantisek Bucik <bucik@.ics.muni.cz>
 * @author Ondrej Ernst <ondra.ernst@gmail.com>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Vo {

    @NonNull private Long id;
    @NonNull private String name;
    @NonNull private String shortName;

    public Vo(Long id, String name, String shortName) {
        this.setId(id);
        this.setName(name);
        this.setShortName(shortName);
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }

        this.name = name;
    }

    public void setShortName(String shortName) {
        if (shortName.trim().isEmpty()) {
            throw new IllegalArgumentException("shortName cannot or empty");
        }

        this.shortName = shortName;
    }

}
