package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

/**
 * The {@code ModelDTO} class is a data transfer object that encapsulates model-related information.
 * It is primarily used to gather and organize model information derived from {@code SimModel} annotations.
 * <p>
 * Each instance of {@code ModelDTO} represents a unique model, identified by its name and class name.
 * <p>
 * This class provides getter and setter methods for the model name and class name.
 */
public class ModelDTO {

    /**
     * The name of the model.
     */
    public String name;

    /**
     * The class name of the model.
     */
    public ClassName className;

    /**
     * Returns the name of the model.
     *
     * @return The name of the model.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of the model.
     *
     * @param name The name of the model.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the class name of the model.
     *
     * @return The class name of the model.
     */
    public ClassName getClassName() {
        return className;
    }

    /**
     * Sets the class name of the model.
     *
     * @param className The class name of the model.
     */
    public void setClassName(ClassName className) {
        this.className = className;
    }

}
