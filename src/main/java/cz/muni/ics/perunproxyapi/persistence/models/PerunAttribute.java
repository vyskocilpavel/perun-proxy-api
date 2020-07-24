package cz.muni.ics.perunproxyapi.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Perun Attribute model
 *
 * @author Dominik Frantisek Bucik <bucik@.ics.muni.cz>
 * @author Ondrej Ernst <ondra.ernst@gmail.com>
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PerunAttribute extends PerunAttributeDefinition {

    @NonNull private PerunAttributeValue value;

    public PerunAttribute(Long id, String friendlyName, String namespace, String description, String type,
                          String displayName, boolean writable, boolean unique, String entity, String baseFriendlyName,
                          String friendlyNameParameter, PerunAttributeValue value) {
        super(id, friendlyName, namespace, description, type, displayName,
                writable, unique, entity, baseFriendlyName, friendlyNameParameter);
        this.setValue(value);
    }

    @Override
    @JsonIgnore
    public String getUrn() {
        return super.getUrn();
    }

    public ObjectNode toJson() {
        ObjectNode defInJson = super.toJson();
        defInJson.set("value", value.valueAsJson());

        return defInJson;
    }

}
