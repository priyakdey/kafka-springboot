package com.priyakdey.kafka.producer.service.impl;

import com.priyakdey.kafka.producer.model.Product;
import com.priyakdey.kafka.producer.service.ProductService;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Priyak Dey
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final String TOPIC_PRODUCT_CREATED = "product-created-events-topic";
    private static final AtomicInteger id = new AtomicInteger(1);

    private final KafkaTemplate<Integer, Product> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<Integer, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Integer createNewProduct(Product product) {
        int productId = id.getAndIncrement();
        product.setId(productId);

        LOGGER.info("Publishing product_created_event to {} with data {}", TOPIC_PRODUCT_CREATED,
            product);

        // send asynchronously
        kafkaTemplate.send(TOPIC_PRODUCT_CREATED, productId, product)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    LOGGER.error("Could not publish new product. Error = {}", ex.getMessage());
                } else {
                    LOGGER.info("Published new product. {}", result.getRecordMetadata());
                }
        });

        return productId;
    }
}
