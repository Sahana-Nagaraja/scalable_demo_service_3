package com.bits.wilp.demo_service_3.notifier;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;

@Configuration
public class ConsumerConfiguration {
    @Bean
    public ConsumerFactory<String, String> customerConsumerFactory() {

        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificationGroup");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, Order> orderConsumerFactory() {
        var orderDeserializer = new JsonDeserializer<>(Order.class);
        orderDeserializer.setRemoveTypeHeaders(false);
        orderDeserializer.addTrustedPackages("*");
        orderDeserializer.setUseTypeMapperForKey(true);

        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notificationGroup");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                orderDeserializer);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaCustomerListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(customerConsumerFactory());
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Order>> kafkaOrderListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Order>();
        factory.setConsumerFactory(orderConsumerFactory());
        return factory;
    }
}