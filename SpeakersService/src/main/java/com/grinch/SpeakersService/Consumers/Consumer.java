package com.grinch.SpeakersService.Consumers;

import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.grinch.SpeakersService.BusinessLogic.Manufacturer;
import com.grinch.SpeakersService.Services.SpeakersService;

@Component
public class Consumer {
	
	/*private static Logger logger = LogManager.getLogger(Consumer.class);
	@Autowired
	private SpeakersService service;
	
    @JmsListener(destination = "${activemq.topics.manufacturers}", containerFactory = "topicListenerFactory")
    public void receiveTopicMessage(@Payload Manufacturer manufacturer,
                                    @Headers MessageHeaders headers,
                                    Message message,
                                    Session session) {
    	logger.info("received <" + manufacturer + ">");
    }*/

}
