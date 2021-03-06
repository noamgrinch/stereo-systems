package com.grinch.ManufacturersService.Services;

import java.util.Optional;
import javax.jms.Topic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private Logger logger = LogManager.getLogger(ManufacturersService.class);
	@Autowired
	private ManufacturersRepository repository;
	@Autowired
	private ManufacturerPublisher publisher;
	@Autowired
	private Topic manuTopic;
	@Autowired
	private ClientOriginsServiceImpl ORIGINS_SERVICE;
	
	public Manufacturer getManufacturer(Long id) throws Exception {
		Optional<Manufacturer> manufacturer = repository.findById(id);
		if(manufacturer.isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + id + " was not found.");
		}
		return manufacturer.get();
	}
	
	public Manufacturer postManufacturer(Manufacturer manufacturer) throws Exception {
		logger.info("Calling OriginService to validate origin: " + manufacturer.getOrigin());
		if(!ORIGINS_SERVICE.validate(manufacturer.getOrigin())) {
			throw new IllegalArgumentException("Origin does not exists.");
		}
		if(!repository.findByName(manufacturer.getName()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		Manufacturer result = repository.save(manufacturer);
		return result;
	}
	
	public Manufacturer putManufacturer(Manufacturer manufacturer) throws Exception {
		if(!ORIGINS_SERVICE.validate(manufacturer.getOrigin())) {
			throw new ResourceNotFoundException("Origin does not exists.");
		}
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
	
	public Boolean exists(Long id) {
		return repository.existsById(id);
	}
	
}
