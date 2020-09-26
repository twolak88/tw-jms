/**
 * 
 */
package com.twolak.springframework.twjms.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.twolak.springframework.twjms.config.JmsConfig;
import com.twolak.springframework.twjms.model.HelloWorldMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author twolak
 *
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class HelloSender {

	private final JmsTemplate jmsTemplate;
	
	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		log.info("Sending message...");
		HelloWorldMessage helloWorldMessage = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hello World!")
				.build();
		jmsTemplate.convertAndSend(JmsConfig.QUEUE, helloWorldMessage);
		log.info("Message has been sent");
	}
}
