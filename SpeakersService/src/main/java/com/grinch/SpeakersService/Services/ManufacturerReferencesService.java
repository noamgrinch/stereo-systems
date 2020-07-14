package com.grinch.SpeakersService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grinch.SpeakersService.Repositories.ManufacturerReferenceRepository;
@Service
public class ManufacturerReferencesService {
	
	@Autowired
	private ManufacturerReferenceRepository repository;
}
