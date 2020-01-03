package com.upgrade.poc.rabbitproducer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@EnableScheduling
@SpringBootApplication
public class RabbitProducerApplication {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${queue.spectrum.name}")
	private String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@PostConstruct
	public void prepareTemplate() {
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitProducerApplication.class, args);
	}

}
