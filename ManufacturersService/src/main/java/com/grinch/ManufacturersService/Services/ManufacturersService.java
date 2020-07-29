package com.grinch.ManufacturersService.Services;

import java.util.Optional;
import javax.jms.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.grinch.ManufacturersService.BusinessLogic.Origin;
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
	@Autowired
	private RestTemplate restTemplate;
	@Value("${Services.Origins.Uri}")
	private String ORIGINS_SERVICE;
	
	public Manufacturer getManufacturer(Long id) throws Exception {
		Optional<Manufacturer> manufacturer = repository.findById(id);
		if(manufacturer.isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + id + " was not found.");
		}
		return manufacturer.get();
	}
	
	public Manufacturer postManufacturer(Manufacturer manufacturer) throws Exception {
		if(!this.validateOrigin(manufacturer.getOrigin())) {
			throw new IllegalArgumentException("Origin does not exists.");
		}
		if(!repository.findByName(manufacturer.getName()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		Manufacturer result = repository.save(manufacturer);
		return result;
	}
	
	public Manufacturer putManufacturer(Manufacturer manufacturer) throws Exception {
		if(!this.validateOrigin(manufacturer.getOrigin())) {
			throw new IllegalArgumentException("Origin does not exists.");
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
	
	private Boolean validateOrigin(Origin origin) {
		HttpEntity<Origin> entity = new HttpEntity<>(origin,null);
		ResponseEntity<Boolean> response = restTemplate.exchange(ORIGINS_SERVICE, HttpMethod.POST, entity, Boolean.class);
		return response.getBody();
	}
}
