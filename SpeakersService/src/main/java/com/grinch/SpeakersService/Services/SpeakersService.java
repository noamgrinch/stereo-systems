package com.grinch.SpeakersService.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Repositories.SpeakersRepository;

@Service
public class SpeakersService {
	
	@Autowired
	private SpeakersRepository repository;
	
	public Speaker getSpeaker(Long id) throws Exception {
		Optional<Speaker> speaker = repository.findById(id);
		if(speaker.isEmpty()) {
			throw new Exception("Speaker with id " + id + " was not found."); // Should create a custom exception and handler.
		}
		return speaker.get();
	}
	
	public Speaker postSpeaker(Speaker speaker) throws Exception {
		if(!repository.findByNameAndManufacturerReference_Id(speaker.getName(), speaker.getManufacturerReference().getId()).isEmpty()) {
			// Should create a custom exception and handler.
			throw new Exception("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(speaker);
	}
	
	public Speaker putSpeaker(Speaker speaker) throws Exception {
		if(repository.findById(speaker.getId()).isEmpty()) {
			// Should create a custom exception and handler.
			throw new Exception("Speaker with id " + speaker.getId() + " was not found."); // Should create a custom exception and handler.
		}
		Optional<Speaker> test = repository.findByNameAndManufacturerReference_Id(speaker.getName(), speaker.getId());
		if((!test.isEmpty())&&test.get().getId()!=speaker.getId()) {
			// Should create a custom exception and handler.
			throw new Exception("Speaker with name " + speaker.getName() + " is already exists for this manufacturer.");
		}
		return repository.save(speaker);
	}
	
	public void deleteSpeaker(Long id) {
		repository.deleteById(id);
	}

}
