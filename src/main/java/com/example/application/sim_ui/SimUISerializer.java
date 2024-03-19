package com.example.application.sim_ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class SimUISerializer implements Serializer<SimUI>
{
    public static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @Override
    public byte[] serialize(String topic, SimUI sim_ui) {
        try {
            return mapper.writeValueAsBytes(sim_ui);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e);
        }
    }

}
