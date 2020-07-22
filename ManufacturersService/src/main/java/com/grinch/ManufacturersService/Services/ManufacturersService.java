package com.grinch.ManufacturersService.Services;

import java.util.Optional;
import javax.jms.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ManufacturersService.Exceptions.ResourceNotFoundException;
import com.grinch.ManufacturersService.Publishers.ManufacturerPublisher;
import com.grinch.ManufacturersService.Repositories.ManufacturersRepository;


@Service
@Transactional
public class ManufacturersService {
	@Autowired
	private ManufacturersRepository repository;
	@Autowired
	private ManufacturerPublisher publisher;
	@Autowired
	private Topic manuTopic;
	
	public Manufacturer getManufacturer(Long id) throws Exception {
		Optional<Manufacturer> manufacturer = repository.findById(id);
		if(manufacturer.isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + id + " was not found.");
		}
		return manufacturer.get();
	}
	
	public Manufacturer postManufacturer(Manufacturer manufacturer) throws Exception {
		//Test Location via external microservice!!
		if(!repository.findByName(manufacturer.getName()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		Manufacturer result = repository.save(manufacturer);
		return result;
	}
	
	public Manufacturer putManufacturer(Manufacturer manufacturer) throws Exception {
		
		if(repository.findById(manufacturer.getId()).isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + manufacturer.getId() + " was not found.");
		}
		Optional<Manufacturer> test = repository.findByName(manufacturer.getName());
		if((!test.isEmpty())&&test.get().getId()!=manufacturer.getId()) {
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		return repository.save(manufacturer);
	}
	
	public void deleteManufacturer(Long id) throws ResourceNotFoundException {
		Optional<Manufacturer> manufacturer = repository.findById(id);
		if(manufacturer.isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + id + " was not found.");
		}
		repository.deleteById(id);
		publisher.publishManufacturer(manuTopic, manufacturer.get()); //publish manufacturer in topic to delete related items.
	}
}
