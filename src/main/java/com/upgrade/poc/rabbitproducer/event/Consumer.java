package com.upgrade.poc.rabbitproducer.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = "rabbit-consumer-enabled", havingValue = "true")
@RabbitListener(queues = "${queue.spectrum.name}")
public class Consumer {

    @RabbitHandler
    public void listen(String message) {
        log.info("Message consumed: {}", message);
    }
}