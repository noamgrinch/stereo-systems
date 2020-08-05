package com.grinch.ElementsService.Services;

import java.util.List;
import java.util.Optional;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.grinch.ElementsService.BusinessLogic.Manufacturer;
import com.grinch.ElementsService.BusinessLogic.Entites.Element;
import com.grinch.ElementsService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ElementsService.Exceptions.ResourceNotFoundException;
import com.grinch.ElementsService.Exceptions.StereoFiException;
import com.grinch.ElementsService.Repositories.ElementsRepository;





@Service
public class ElementsService {
	
	@Autowired
	private ElementsRepository repository;
	@Autowired
	private GRPCManufacturersService grpcManuService;
	
	public Element getElement(Long id) throws ResourceNotFoundException {
		Optional<Element> result = repository.findById(id);
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("Element with id " + id + " was not found."); 
		}
		return result.get();
	}
	
	public Element postElement(Element element) throws StereoFiException {
		//Validate manufacturer via ManufacturersService.
		if(!grpcManuService.validate(element.getManufacturerReference().getId())) {
			throw new StereoFiException("Invalid manufacturer ID.",HttpStatus.BAD_REQUEST);
		}
		if(!repository.findByNameAndManufacturerReference_Id(element.getName(), element.getManufacturerReference().getId()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Element with name " + element.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(element);
	}
	
	public Element putElement(Element element) throws StereoFiException {
		//Validate manufacturer via ManufacturersService.
		if(!grpcManuService.validate(element.getManufacturerReference().getId())) {
			throw new StereoFiException("Invalid manufacturer ID.",HttpStatus.BAD_REQUEST);
		}
		if(repository.findById(element.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Element with id " + element.getId() + " was not found.");
		}
		Optional<Element> test = repository.findByNameAndManufacturerReference_Id(element.getName(), element.getManufacturerReference().getId());
		if((!test.isEmpty())&&test.get().getId()!=element.getId()) {
			throw new ResourceAlreadyExistsException("Element with name " + element.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(element);
	}
	
	public void deleteElement(Long id) throws ResourceNotFoundException {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Element with id " + id + " was not found.");
		}
	}
	
	@JmsListener(destination = "${activemq.topics.manufacturers}", containerFactory = "topicListenerFactory")
	public void deleteSpeakersByManufacturer(@Payload Manufacturer manufacturer,
            @Headers MessageHeaders headers,
            Message message,
            Session session) {
		List<Element> speakers = repository.findByManufacturerReference_Id(manufacturer.getId());
		repository.deleteAll(speakers);
	}

}
