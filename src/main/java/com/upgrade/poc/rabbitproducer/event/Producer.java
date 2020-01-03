package com.upgrade.poc.rabbitproducer.event;

import com.upgrade.poc.rabbitproducer.model.SpectrumMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    private RabbitTemplate rabbitTemplate;

    private Queue queue;

    public Producer(RabbitTemplate template, Queue queue) {
        this.rabbitTemplate = template;
        this.queue = queue;
    }

    @Scheduled(fixedDelay = 5000)
    public void produce() {
        SpectrumMessage message = new SpectrumMessage();
        message.setAmount("$125.00");
        message.setTransaction("PAYMENT");

        rabbitTemplate.convertAndSend(queue.getName(), message);

        log.info("Sending spectrum message={}, queue={}", message, queue.getName());
    }
}
