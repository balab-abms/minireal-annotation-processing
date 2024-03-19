package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

public class ModelDTO {
    public String name;
    public ClassName className;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassName getClassName() {
        return className;
    }

    public void setClassName(ClassName className) {
        this.className = className;
    }

}
