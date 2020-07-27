package com.grinch.ReceiversService.Services;

import java.util.List;
import java.util.Optional;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.grinch.ReceiversService.BusinessLogic.Manufacturer;
import com.grinch.ReceiversService.BusinessLogic.Entities.Receiver;
import com.grinch.ReceiversService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ReceiversService.Exceptions.ResourceNotFoundException;
import com.grinch.ReceiversService.Repositories.ReceiversRepository;


@Service
public class ReceiversService {
	@Autowired
	private ReceiversRepository repository;
	
	public Receiver getReceiver(Long id) throws ResourceNotFoundException {
		Optional<Receiver> result = repository.findById(id);
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("Receiver with id " + id + " was not found."); 
		}
		return result.get();
	}
	
	public Receiver postReceiver(Receiver Receiver) throws ResourceAlreadyExistsException {
		if(!repository.findByNameAndManufacturerReference_Id(Receiver.getName(), Receiver.getManufacturerReference().getId()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Receiver with name " + Receiver.getName() + " is already exists for this Receiver.");
		}
		return repository.save(Receiver);
	}
	
	public Receiver putReceiver(Receiver Receiver) throws ResourceAlreadyExistsException, ResourceNotFoundException {
		if(repository.findById(Receiver.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Receiver with id " + Receiver.getId() + " was not found."); // Should create a custom exception and handler.
		}
		Optional<Receiver> test = repository.findByNameAndManufacturerReference_Id(Receiver.getName(), Receiver.getManufacturerReference().getId());
		if((!test.isEmpty())&&test.get().getId()!=Receiver.getId()) {
			throw new ResourceAlreadyExistsException("Receiver with name " + Receiver.getName() + " is already exists for this Receiver.");
		}
		return repository.save(Receiver);
	}
	
	public void deleteReceiver(Long id) throws ResourceNotFoundException {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Receiver with id " + id + " was not found.");
		}
		repository.deleteById(id);
	}
	
	@JmsListener(destination = "${activemq.topics.manufacturers}", containerFactory = "topicListenerFactory")
	public void deleteSpeakersByManufacturer(@Payload Manufacturer manufacturer,
            @Headers MessageHeaders headers,
            Message message,
            Session session) {
		List<Receiver> speakers = repository.findByManufacturerReference_Id(manufacturer.getId());
		repository.deleteAll(speakers);
	}
}
