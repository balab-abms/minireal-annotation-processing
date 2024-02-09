package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;
import org.simreal.annotation.SimField;

import java.util.List;

public class DatabaseDTO {
    public String tableName;
    public String methodName;
    public ClassName boundedAgentName;
    public List<FieldDTO> boundedAgentFields;

    public void setBoundedAgentName(ClassName boundedAgentName) {
        this.boundedAgentName = boundedAgentName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setBoundedAgentFields(List<FieldDTO> boundedAgentFields) {
        this.boundedAgentFields = boundedAgentFields;
    }

    public ClassName getBoundedAgentName() {
        return boundedAgentName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<FieldDTO> getBoundedAgentFields() {
        return boundedAgentFields;
    }
}
