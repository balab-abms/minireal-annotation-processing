package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

public class FieldDTO {
    public String name;
    public String type;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
