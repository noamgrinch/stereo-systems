package com.grinch.ManufacturersService.Publishers;

import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;

@Service
public class ManufacturerPublisher {
	
	@Autowired
	JmsTemplate jmsTemplate;


	public void publishManufacturer(final Topic topic, final Manufacturer manufacturer) {
		jmsTemplate.convertAndSend(topic, manufacturer);
	}
}
