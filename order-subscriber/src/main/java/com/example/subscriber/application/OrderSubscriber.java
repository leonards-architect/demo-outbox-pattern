package com.example.subscriber.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderSubscriber {

    @KafkaListener(topics = "sampledb.sample.orders", groupId = "my-group")
    public void subscribe(String message) {
        log.info("Received message: " + message);
    }
}
