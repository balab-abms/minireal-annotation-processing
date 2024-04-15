package com.example.application.kafkaserialize;

import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * The {@code KafkaTemplateSerializer} class provides a mechanism for serializing and deserializing
 * a {@code KafkaTemplate} object. This is particularly useful when you want to persist the state of
 * a {@code KafkaTemplate} object or transmit it over a network.
 * <p>
 * This class implements the {@code Serializable} interface, which means it can be converted into a byte stream
 * and recovered later.
 */
public class KafkaTemplateSerializer implements Serializable
{
    /**
     * The {@code KafkaTemplate} object to be serialized/deserialized.
     */
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Constructs a new {@code KafkaTemplateSerializer} with the specified {@code KafkaTemplate}.
     *
     * @param kafkaTemplate the {@code KafkaTemplate} to be serialized/deserialized
     */
    public KafkaTemplateSerializer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Serializes the {@code KafkaTemplate} object by writing its configuration parameters to the specified
     * {@code ObjectOutputStream}.
     *
     * @param oos the {@code ObjectOutputStream} to which the object is to be written
     * @throws IOException if an I/O error occurs while writing to the {@code ObjectOutputStream}
     */
    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        // Serialize the KafkaTemplate configuration parameters
        oos.writeObject(kafkaTemplate.getProducerFactory().getConfigurationProperties());
    }

    /**
     * Deserializes the {@code KafkaTemplate} object by reading its configuration parameters from the specified
     * {@code ObjectInputStream} and creating a new {@code KafkaTemplate} with these parameters.
     *
     * @param ois the {@code ObjectInputStream} from which the object is to be read
     * @throws IOException if an I/O error occurs while reading from the {@code ObjectInputStream}
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // Deserialize the KafkaTemplate configuration parameters
        Map<String, Object> configurationProperties = (Map<String, Object>) ois.readObject();
        ProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configurationProperties);
        this.kafkaTemplate = new KafkaTemplate<>(producerFactory);
    }

    /**
     * Returns the {@code KafkaTemplate} object associated with this {@code KafkaTemplateSerializer}.
     *
     * @return the {@code KafkaTemplate} object associated with this {@code KafkaTemplateSerializer}
     */
    public KafkaTemplate<String, Object> getKafkaTemplate() {
        return kafkaTemplate;
    }
}

