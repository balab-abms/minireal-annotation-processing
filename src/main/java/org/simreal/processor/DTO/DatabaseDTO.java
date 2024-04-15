package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

import java.util.List;

/**
 * The {@code DatabaseDTO} class is a data transfer object that encapsulates database-related information.
 * It is primarily used to gather and organize database information derived from {@code SimDB} annotations.
 * <p>
 * Each instance of {@code DatabaseDTO} represents a unique database table, identified by its name and associated method.
 * The table name, method name, and the name of the bounded agent are all represented as strings.
 * The fields of the bounded agent are represented as a list of {@code FieldDTO} objects.
 * <p>
 * This class provides getter and setter methods for the table name, method name, bounded agent name, and bounded agent fields.
 */
public class DatabaseDTO {

    /**
     * The name of the database table.
     */
    public String tableName;

    /**
     * The name of the method associated with the database table.
     */
    public String methodName;

    /**
     * The name of the bounded agent associated with the database table.
     */
    public ClassName boundedAgentName;

    /**
     * The fields of the bounded agent associated with the database table.
     */
    public List<FieldDTO> boundedAgentFields;

    /**
     * Sets the name of the bounded agent associated with the database table.
     *
     * @param boundedAgentName The name of the bounded agent.
     */
    public void setBoundedAgentName(ClassName boundedAgentName) {
        this.boundedAgentName = boundedAgentName;
    }

    /**
     * Sets the name of the method associated with the database table.
     *
     * @param methodName The name of the method.
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * Sets the name of the database table.
     *
     * @param tableName The name of the database table.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Sets the fields of the bounded agent associated with the database table.
     *
     * @param boundedAgentFields The fields of the bounded agent.
     */
    public void setBoundedAgentFields(List<FieldDTO> boundedAgentFields) {
        this.boundedAgentFields = boundedAgentFields;
    }

    /**
     * Returns the name of the bounded agent associated with the database table.
     *
     * @return The name of the bounded agent.
     */

    public ClassName getBoundedAgentName() {
        return boundedAgentName;
    }

    /**
     * Returns the name of the method associated with the database table.
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Returns the name of the database table.
     *
     * @return The name of the database table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Returns the fields of the bounded agent associated with the database table.
     *
     * @return The fields of the bounded agent.
     */
    public List<FieldDTO> getBoundedAgentFields() {
        return boundedAgentFields;
    }
}
