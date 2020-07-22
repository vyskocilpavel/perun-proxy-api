package cz.muni.ics.perunproxyapi.persistence.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.ToString;


/**
 * Model for ExtSource
 *
 * @author Dominik Frantisek Bucik <bucik@ics.muni.cz>
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class ExtSource extends Model {

    @Getter
    private String name;
    @Getter
    private String type;

    public ExtSource() {
    }

    public ExtSource(Long id, String name, String type) {
        super(id);
        this.setName(name);
        this.setType(type);
    }


    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("name cannot be null nor empty");
        }

        this.name = name;
    }


    public void setType(String type) {
        if (type == null || type.length() == 0) {
            throw new IllegalArgumentException("type cannot be null nor empty");
        }

        this.type = type;
    }
}
