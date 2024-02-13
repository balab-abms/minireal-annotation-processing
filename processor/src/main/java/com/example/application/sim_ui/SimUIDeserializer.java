package com.example.application.sim_ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
public class SimUIDeserializer implements Deserializer<SimUI>
{
    public static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public SimUI deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, SimUI.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
