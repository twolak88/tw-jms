package com.twolak.springframework.twjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwJmsApplication {

	public static void main(String[] args) throws Exception {
		
		//Embedded activemq artemis server configuration
//		ActiveMQServer activeMQServer = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
//				.setPersistenceEnabled(false)
//				.setJournalDirectory("target/data/journal")
//				.setSecurityEnabled(false)
//				.addAcceptorConfiguration("invm", "vm://0"));
//		activeMQServer.start();
		
		SpringApplication.run(TwJmsApplication.class, args);
	}

}
