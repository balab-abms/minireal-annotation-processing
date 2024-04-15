package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

/**
 * The {@code ParamDTO} class is a data transfer object that encapsulates parameter-related information.
 * It is primarily used to gather and organize parameter information derived from {@code SimParam} annotations.
 * <p>
 * Each instance of {@code ParamDTO} represents a unique parameter, identified by its name, value, type, and default status.
 * <p>
 * This class provides getter and setter methods for the parameter name, value, type, and default status.
 */
public class ParamDTO {

    /**
     * The name of the parameter.
     */
    public String name;

    /**
     * The value of the parameter.
     */
    public String value;

    /**
     * The type of the parameter.
     */
    public String type;

    /**
     * The default status of the parameter. If true, this parameter is a default parameter.
     */
    public boolean isdefault;

    /**
     * Returns the name of the parameter.
     *
     * @return The name of the parameter.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of the parameter.
     *
     * @return The type of the parameter.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the parameter.
     *
     * @param type The type of the parameter.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the value of the parameter.
     *
     * @return The value of the parameter.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the parameter.
     *
     * @param value The value of the parameter.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the default status of the parameter.
     *
     * @return True if this parameter is a default parameter, false otherwise.
     */
    public boolean isIsdefault() {
        return isdefault;
    }

    /**
     * Sets the default status of the parameter.
     *
     * @param isdefault True if this parameter is a default parameter, false otherwise.
     */
    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }
}
