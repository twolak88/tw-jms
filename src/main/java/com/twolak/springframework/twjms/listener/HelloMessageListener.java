/**
 * 
 */
package com.twolak.springframework.twjms.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
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
public class HelloMessageListener {
	
	private final JmsTemplate jmsTemplate;
	
	@JmsListener(destination = JmsConfig.QUEUE_SEND)
	public void listen(/*required*/@Payload HelloWorldMessage helloWorldMessage, 
			@Headers MessageHeaders messageHeaders, Message message) {
//		log.info("Receiving message");
//		
//		
//		log.info(helloWorldMessage.toString());
//		will be re-delivered to confirmation from client 
//		throw new RuntimeException("exception");
	}
	
	@JmsListener(destination = JmsConfig.QUEUE_SEND_AND_RECV)
	public void listenRecv(/*required*/@Payload HelloWorldMessage helloWorldMessage, 
			@Headers MessageHeaders messageHeaders, Message jmsMessage, 
			org.springframework.messaging.Message<HelloWorldMessage> springMessage) throws JmsException, JMSException {
		
		HelloWorldMessage responseMessage = HelloWorldMessage
				.builder()
				.id(UUID.randomUUID())
				.message(helloWorldMessage.getMessage() + " Response!")
				.build();
		jmsTemplate.convertAndSend(jmsMessage.getJMSReplyTo(), responseMessage);
	}
}
