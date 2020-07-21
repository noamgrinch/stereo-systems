package com.grinch.SpeakersService.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Exceptions.ResourceAlreadyExistsException;
import com.grinch.SpeakersService.Exceptions.ResourceNotFoundException;
import com.grinch.SpeakersService.Repositories.SpeakersRepository;

@Service
public class SpeakersService {
	
	@Autowired
	private SpeakersRepository repository;

	
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
	
	public void deleteSpeaker(Long id) {
		repository.deleteById(id);
	}

}
