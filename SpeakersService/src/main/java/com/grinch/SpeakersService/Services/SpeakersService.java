package com.grinch.SpeakersService.Services;

import java.util.List;
import java.util.Optional;

import javax.jms.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.grinch.SpeakersService.BusinessLogic.Manufacturer;
import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.SpeakersService.Exceptions.ResourceNotFoundException;
import com.grinch.SpeakersService.Repositories.SpeakersRepository;

@Service
public class SpeakersService {
	
	@Autowired
	private SpeakersRepository repository;
	private static Logger logger = LogManager.getLogger(SpeakersService.class);
	
	public Speaker getSpeaker(Long id) throws Exception {
		Optional<Speaker> speaker = repository.findById(id);
		if(speaker.isEmpty()) {
			throw new ResourceNotFoundException("Speaker with id " + id + " was not found."); // Should create a custom exception handler.
		}
		Speaker result = speaker.get();
		return result;
	}
	
	public Speaker postSpeaker(Speaker speaker) throws Exception {
		// Check via ManufacturersService that manufacturer exists!!
		if(!repository.findByNameAndManufacturerReference_Id(speaker.getName(), speaker.getManufacturerReference().getId()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(speaker);
	}
	
	public Speaker putSpeaker(Speaker speaker) throws Exception {
		if(repository.findById(speaker.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Speaker with id " + speaker.getId() + " was not found."); // Should create a custom exception and handler.
		}
		Optional<Speaker> test = repository.findByNameAndManufacturerReference_Id(speaker.getName(), speaker.getManufacturerReference().getId());
		if((!test.isEmpty())&&test.get().getId()!=speaker.getId()) {
			throw new ResourceAlreadyExistsException("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(speaker);
	}
	
	public void deleteSpeaker(Long id) throws ResourceNotFoundException {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Speaker with id " + id + " was not found.");
		}
		repository.deleteById(id);
	}
	
	@JmsListener(destination = "${activemq.topics.manufacturers}", containerFactory = "topicListenerFactory")
	public void deleteSpeakersByManufacturer(@Payload Manufacturer manufacturer,
            @Headers MessageHeaders headers,
            Message message,
            Session session) {
		logger.info("Consumed deletion operation for manufacturer: " + manufacturer.toString());
		List<Speaker> speakers = repository.findByManufacturerReference_Id(manufacturer.getId());
		repository.deleteAll(speakers);
	}

}
