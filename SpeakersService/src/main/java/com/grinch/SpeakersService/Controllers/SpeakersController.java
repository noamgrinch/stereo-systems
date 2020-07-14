package com.grinch.SpeakersService.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grinch.SpeakersService.BusinessLogic.Entites.Speaker;
import com.grinch.SpeakersService.Services.SpeakersService;

@RestController
@RequestMapping("/speakers")
public class SpeakersController {
	
	@Autowired
	private SpeakersService service;

	@GetMapping("/{id}")
	public Speaker getSpeaker(@PathVariable("id") Long id) throws Exception {
		return service.getSpeaker(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSpeaker(@PathVariable("id") Long id) throws Exception {
		service.deleteSpeaker(id);
	}
	
	@PostMapping()
	public Speaker postSpeaker(@RequestBody @Valid Speaker speaker) throws Exception {
		return service.postSpeaker(speaker);
	}
	
	@PutMapping()
	public Speaker updateSpeaker(@RequestBody @Valid Speaker speaker) throws Exception {
		return service.putSpeaker(speaker);
	}
	
}
