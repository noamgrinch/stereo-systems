package com.grinch.ManufacturersService.Publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;

@Component
public class ManufacturerPublisher {
	
	@Autowired
	JmsTemplate jmsTemplate;


	public void publishManufacturer(final String topicName, final Manufacturer manufacturer) {
		jmsTemplate.convertAndSend(topicName, manufacturer);
	}
}
