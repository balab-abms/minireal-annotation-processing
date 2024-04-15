package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

/**
 * The {@code FieldDTO} class is a data transfer object that encapsulates field-related information.
 * It is primarily used to gather and organize field information derived from {@code SimField} annotations.
 * <p>
 * Each instance of {@code FieldDTO} represents a unique field, identified by its name and type.
 * The field name and type are both represented as strings.
 * <p>
 * This class provides getter and setter methods for the field name and type.
 */
public class FieldDTO {

    /**
     * The name of the field.
     */
    public String name;

    /**
     * The type of the field.
     */
    public String type;

    /**
     * Sets the name of the field.
     *
     * @param name The name of the field.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the field.
     *
     * @param type The type of the field.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the name of the field.
     *
     * @return The name of the field.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the field.
     *
     * @return The type of the field.
     */
    public String getType() {
        return type;
    }
}
