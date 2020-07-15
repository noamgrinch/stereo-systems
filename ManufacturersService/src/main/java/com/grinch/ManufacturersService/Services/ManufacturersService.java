package com.grinch.ManufacturersService.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ManufacturersService.Exceptions.ResourceNotFoundException;
import com.grinch.ManufacturersService.Repositories.ManufacturersRepository;


@Service
public class ManufacturersService {
	@Autowired
	private ManufacturersRepository repository;

	
	public Manufacturer getManufacturer(Long id) throws Exception {
		Optional<Manufacturer> Manufacturer = repository.findById(id);
		if(Manufacturer.isEmpty()) {
			throw new ResourceNotFoundException("Manufacturer with id " + id + " was not found."); // Should create a custom exception and handler.
		}
		return Manufacturer.get();
	}
	
	public Manufacturer postManufacturer(Manufacturer manufacturer) throws Exception {
		//Test Location via external microservice!!
		if(!repository.findByName(manufacturer.getName()).isEmpty()) {
			// Should create a custom exception and handler.
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		return repository.save(manufacturer);
	}
	
	public Manufacturer putManufacturer(Manufacturer manufacturer) throws Exception {
		if(repository.findById(manufacturer.getId()).isEmpty()) {
			// Should create a custom exception and handler.
			throw new ResourceNotFoundException("Manufacturer with id " + manufacturer.getId() + " was not found."); // Should create a custom exception and handler.
		}
		if(!repository.findByName(manufacturer.getName()).isEmpty()) {
			// Should create a custom exception and handler.
			throw new ResourceAlreadyExistsException("Manufacturer with name " + manufacturer.getName() + " is already exists.");
		}
		return repository.save(manufacturer);
	}
	
	public void deleteManufacturer(Long id) {
		repository.deleteById(id);
	}
}
