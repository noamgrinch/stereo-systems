package com.grinch.SpeakersService.Controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	private static Logger logger = LogManager.getLogger(SpeakersController.class);

	@GetMapping("/{id}")
	public Speaker getSpeaker(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received get request from " + request.getRemoteAddr());
		return service.getSpeaker(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSpeaker(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received delete request from " + request.getRemoteAddr());
		service.deleteSpeaker(id);
	}
	
	@PostMapping()
	public Speaker postSpeaker(@RequestBody @Valid Speaker speaker, HttpServletRequest  request) throws Exception {
		logger.info("Received post request from " + request.getRemoteAddr());
		return service.postSpeaker(speaker);
	}
	
	@PutMapping()
	public Speaker updateSpeaker(@RequestBody @Valid Speaker speaker, HttpServletRequest  request) throws Exception {
		logger.info("Received update request from " + request.getRemoteAddr());
		return service.putSpeaker(speaker);
	}
	
}
