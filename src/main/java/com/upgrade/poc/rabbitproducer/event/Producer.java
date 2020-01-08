package com.upgrade.poc.rabbitproducer.event;

import com.upgrade.poc.rabbitproducer.model.SpectrumMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

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

        Random random = new Random();
        int randomId = random.ints(0, (14678)).findFirst().getAsInt();

        rabbitTemplate.convertAndSend(queue.getName(), message, m -> {
            m.getMessageProperties().setMessageId(String.valueOf(randomId));
            m.getMessageProperties().setContentEncoding("utf-8");
            m.getMessageProperties().setContentType("application/json");
            return m;
        });

        log.info("Sending spectrum message={}, queue={}", message, queue.getName());
    }
}
