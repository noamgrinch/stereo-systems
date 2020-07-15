package com.grinch.SpeakersService.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grinch.SpeakersService.BusinessLogic.Entites.ManufacturerReference;
import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.SpeakersService.Exceptions.ResourceNotFoundException;
import com.grinch.SpeakersService.Repositories.SpeakersRepository;

@Service
public class SpeakersService {
	
	@Autowired
	private SpeakersRepository repository;
	@Autowired
	private ManufacturerReferencesService manuService;
	
	public Speaker getSpeaker(Long id) throws Exception {
		Optional<Speaker> speaker = repository.findById(id);
		if(speaker.isEmpty()) {
			throw new ResourceNotFoundException("Speaker with id " + id + " was not found."); // Should create a custom exception handler.
		}
		Speaker result = speaker.get();
		result.setManufacturerReference(new ManufacturerReference(result.getManufacturerReference().getId(),result.getManufacturerReference().getName()));
		return result;
	}
	
	public Speaker postSpeaker(Speaker speaker) throws Exception {
		if(!manuService.exists(speaker.getManufacturerReference())) {
			// Should create a custom handler.
			throw new Exception("Manufacturer with id " + speaker.getManufacturerReference().getManufacturerId() + " does not exists.");
		}
		if(!repository.findByNameAndManufacturerId(speaker.getName(), speaker.getManufacturerReference().getId()).isEmpty()) {
			// Should create a custom handler.
			throw new ResourceAlreadyExistsException("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		speaker.setManufacturerId(speaker.getManufacturerReference().getId());
		speaker.setManufacturerName(speaker.getManufacturerReference().getName());
		return repository.save(speaker);
	}
	
	public Speaker putSpeaker(Speaker speaker) throws Exception {
		if(repository.findById(speaker.getId()).isEmpty()) {
			// Should create a custom exception handler.
			throw new ResourceNotFoundException("Speaker with id " + speaker.getId() + " was not found."); // Should create a custom exception and handler.
		}
		Optional<Speaker> test = repository.findByNameAndManufacturerId(speaker.getName(), speaker.getId());
		if((!test.isEmpty())&&test.get().getId()!=speaker.getId()) {
			// Should create a custom handler.
			throw new ResourceAlreadyExistsException("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		speaker.setManufacturerId(speaker.getManufacturerReference().getId());
		speaker.setManufacturerName(speaker.getManufacturerReference().getName());
		return repository.save(speaker);
	}
	
	public void deleteSpeaker(Long id) {
		repository.deleteById(id);
	}

}
