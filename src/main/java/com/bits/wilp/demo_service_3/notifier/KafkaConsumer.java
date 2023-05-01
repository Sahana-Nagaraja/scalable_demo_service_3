package com.bits.wilp.demo_service_3.notifier;

import com.bits.wilp.demo_service_3.customer.Customer;
import com.bits.wilp.demo_service_3.customer.CustomerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(org.apache.kafka.clients.consumer.KafkaConsumer.class);

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    CustomerClient customerClient;

    @KafkaListener(topics = "notifyCustomerCreation", containerFactory = "kafkaCustomerListenerContainerFactory")
    void customerListener(String message) {
        log.info("Listening for customer creation topic.....");
        log.info("Received:********* {}", message);
        Notification notification = new Notification(UUID.randomUUID().toString(), message);
        log.info("Notified the customer about account creation....Notification ID is " + notification.getId());
    }

    @KafkaListener(topics = "notifyOrderCreation", containerFactory = "kafkaOrderListenerContainerFactory")
    void orderListener(Order order) {
        log.info("Listening for order creation topic.....");
        log.info("Received Order notification request for Order ID:********* {}", order.getId());
        Customer customer = customerClient.getCustomer(order.getCustomerId());
        Notification notification = new Notification(UUID.randomUUID().toString(), "Order creation notification sent to Customer ID: " + customer.getFirstName() + " for order ID: " + order.getId());
        log.info("Notified the customer about order creation...." + notification.getId());
    }
}