package com.example.application.kafkaserialize;

import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class KafkaTemplateSerializer implements Serializable
{
    private KafkaTemplate<String, Object> kafkaTemplate;
    public KafkaTemplateSerializer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        // Serialize the KafkaTemplate configuration parameters
        oos.writeObject(kafkaTemplate.getProducerFactory().getConfigurationProperties());
    }
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // Deserialize the KafkaTemplate configuration parameters
        Map<String, Object> configurationProperties = (Map<String, Object>) ois.readObject();
        ProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configurationProperties);
        this.kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }
    public KafkaTemplate<String, Object> getKafkaTemplate() {
        return kafkaTemplate;
    }
}

