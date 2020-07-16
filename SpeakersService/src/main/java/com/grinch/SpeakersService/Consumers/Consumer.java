package com.grinch.SpeakersService.Consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@JmsListener(destination = "${activemq.topics.manufacturers}")
	public void receive(String message) {

		System.out.println("Message received : " +  message );
		     
    }

}
