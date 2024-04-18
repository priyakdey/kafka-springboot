package com.priyakdey.kafka.consumer.deserilizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priyakdey.kafka.consumer.model.Product;
import java.io.IOException;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author Priyak Dey
 */
public class ProductDeserializer implements Deserializer<Product> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic topic associated with the data
     * @param data  serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public Product deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Product.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
