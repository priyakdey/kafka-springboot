package com.priyakdey.kafka.producer.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.priyakdey.kafka.producer.model.Product;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author Priyak Dey
 */
public class ProductSerializer implements Serializer<Product> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Convert {@code data} into a byte array.
     *
     * @param topic topic associated with data
     * @param data  typed data
     * @return serialized bytes
     */
    @Override
    public byte[] serialize(String topic, Product data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
