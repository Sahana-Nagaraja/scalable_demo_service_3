package com.bits.wilp.demo_service_3.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(org.apache.kafka.clients.consumer.KafkaConsumer.class);

    @KafkaListener(topics = "timestamp", containerFactory = "kafkaListenerContainerFactory")
    void listener(TimestampEvent event) {
        log.info("Received: {}", event.getTimestamp()
                .toString());
    }
}