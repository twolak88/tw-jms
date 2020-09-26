/**
 * 
 */
package com.twolak.springframework.twjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author twolak
 *
 */
@Configuration
public class JmsConfig {
	
	public static final String QUEUE_SEND = "hello-world";
	public static final String QUEUE_SEND_AND_RECV = "hello-world-with-repl";
	
	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
		jackson2MessageConverter.setTargetType(MessageType.TEXT);
		jackson2MessageConverter.setTypeIdPropertyName("_type");
		return jackson2MessageConverter;
	}
}
