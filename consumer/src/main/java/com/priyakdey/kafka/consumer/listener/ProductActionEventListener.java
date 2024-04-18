package com.priyakdey.kafka.consumer.listener;

import com.priyakdey.kafka.consumer.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Priyak Dey
 */
@Component
public class ProductActionEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductActionEventListener.class);

    @KafkaListener(topics = "product-created-events-topic")
    public void handleProductCreatedEvent(Product product) {
        LOGGER.info("Consumed one product created event.... Message = {}", product);
    }

}
