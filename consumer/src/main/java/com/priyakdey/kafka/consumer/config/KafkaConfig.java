package com.priyakdey.kafka.consumer.config;

import com.priyakdey.kafka.consumer.deserilizer.ProductDeserializer;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

/**
 * @author Priyak Dey
 */
@Configuration
public class KafkaConfig {

    private final Environment environment;

    public KafkaConfig(Environment environment) {
        this.environment = environment;
    }


    @Bean
    public ConsumerFactory<Integer, Object> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            environment.getProperty("spring.kafka.bootstrap-servers"));
        config.put("spring.kafka.consumer.bootstrap-servers",
            environment.getProperty("spring.kafka.consumer.bootstrap-servers"));
        config.put(ConsumerConfig.CLIENT_ID_CONFIG,
            environment.getProperty("spring.kafka.consumer.client-id"));
        config.put(ConsumerConfig.GROUP_ID_CONFIG,
            environment.getProperty("spring.kafka.consumer.group-id"));

//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
//            ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,
//            IntegerDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
//            ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
//            ProductDeserializer.class);

        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ProductDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Integer, Object> concurrentKafkaListenerContainerFactory(
        ConsumerFactory<Integer, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, Object> containerFactory =
            new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        return containerFactory;
    }

}
