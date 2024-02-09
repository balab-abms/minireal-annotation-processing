package org.simreal.processor.DTO;

import com.squareup.javapoet.ClassName;

public class VisualDTO {
    public String modelMethodName;
    public String agentMethodName;

    public ClassName boundedAgentName;

    public ClassName getBoundedAgentName() {
        return boundedAgentName;
    }

    public void setBoundedAgentName(ClassName boundedAgentName) {
        this.boundedAgentName = boundedAgentName;
    }

    public String getModelMethodName() {
        return modelMethodName;
    }

    public void setModelMethodName(String modelMethodName) {
        this.modelMethodName = modelMethodName;
    }

    public String getAgentMethodName() {
        return agentMethodName;
    }

    public void setAgentMethodName(String agentMethodName) {
        this.agentMethodName = agentMethodName;
    }
}
