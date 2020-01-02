package com.upgrade.poc.rabbitproducer.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    private RabbitTemplate template;

    private Queue queue;

    public Producer(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    @Scheduled(fixedDelay = 5000)
    public void produce() {
        String message = "Test message from RabbitMQ";

        template.convertAndSend(queue.getName(), message);

        log.info("Sending spectrum message={}, queue={}", message, queue.getName());
    }
}
