/**
 * 
 */
package com.twolak.springframework.twjms.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	private final ObjectMapper objectMapper;
	
//	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
//		log.info("Sending message...");
		HelloWorldMessage helloWorldMessage = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hello World!")
				.build();
		jmsTemplate.convertAndSend(JmsConfig.QUEUE_SEND, helloWorldMessage);
//		log.info("Message has been sent");
	}
	
	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() throws JMSException {
//		log.info("Sending message...");
		HelloWorldMessage helloWorldMessage = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hello World!")
				.build();
		Message recvMessage = jmsTemplate.sendAndReceive(JmsConfig.QUEUE_SEND_AND_RECV, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message;
				try {
					message = session.createTextMessage(objectMapper.writeValueAsString(helloWorldMessage));
					message.setStringProperty("_type", "com.twolak.springframework.twjms.model.HelloWorldMessage");
					log.info("Sending recv message...");
					return message;
				} catch (JsonProcessingException e) {
					throw new JMSException(e.getMessage());
				}
			}
		});
		try {
			log.info("Message has been sent and recived: " + objectMapper.readValue(recvMessage.getBody(String.class), HelloWorldMessage.class).getMessage());
		} catch (JsonProcessingException | JMSException e) {
			throw new JMSException(e.getMessage());
		}
	}
}
