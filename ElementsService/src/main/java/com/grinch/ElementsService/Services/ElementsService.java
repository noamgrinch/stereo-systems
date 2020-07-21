package com.grinch.ElementsService.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grinch.ElementsService.BusinessLogic.Entites.Element;
import com.grinch.ElementsService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.ElementsService.Exceptions.ResourceNotFoundException;
import com.grinch.ElementsService.Repositories.ElementsRepository;


@Service
public class ElementsService {
	
	@Autowired
	private ElementsRepository repository;
	
	public Element getElement(Long id) throws ResourceNotFoundException {
		Optional<Element> result = repository.findById(id);
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("Element with id " + id + " was not found."); 
		}
		return result.get();
	}
	
	public Element postElement(Element element) throws ResourceAlreadyExistsException {
		if(!repository.findByNameAndManufacturerReference_Id(element.getName(), element.getManufacturerReference().getId()).isEmpty()) {
			throw new ResourceAlreadyExistsException("Element with name " + element.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(element);
	}
	
	public Element putElement(Element element) throws ResourceAlreadyExistsException, ResourceNotFoundException {
		if(repository.findById(element.getId()).isEmpty()) {
			throw new ResourceNotFoundException("element with id " + element.getId() + " was not found."); // Should create a custom exception and handler.
		}
		Optional<Element> test = repository.findByNameAndManufacturerReference_Id(element.getName(), element.getManufacturerReference().getId());
		if((!test.isEmpty())&&test.get().getId()!=element.getId()) {
			throw new ResourceAlreadyExistsException("element with name " + element.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(element);
	}
	
	public void deleteElement(Long id) {
		repository.deleteById(id);
	}

}
