/**
 * 
 */
package com.twolak.springframework.twjms.listener;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.twolak.springframework.twjms.config.JmsConfig;
import com.twolak.springframework.twjms.model.HelloWorldMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author twolak
 *
 */
@Slf4j
@Component
public class HelloMessageListener {
	
	@JmsListener(destination = JmsConfig.QUEUE)
	public void listen(/*required*/@Payload HelloWorldMessage helloWorldMessage, 
			@ Headers MessageHeaders messageHeaders, Message message) {
		log.info("Receiving message");
		
		
		log.info(helloWorldMessage.toString());
//		will be re-delivered to confirmation from client 
//		t hrow new RuntimeException("exception");
	}
}
