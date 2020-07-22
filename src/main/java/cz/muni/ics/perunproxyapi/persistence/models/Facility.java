package cz.muni.ics.perunproxyapi.persistence.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Facility object model.
 *
 * @author Peter Jancus <jancus@ics.muni.cz>
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class Facility extends Model {

    @Getter
    private String name;
    @Getter
    private String description;

    public Facility() {
    }

    public Facility(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }


    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("name cannot be null nor empty");
        }

        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

