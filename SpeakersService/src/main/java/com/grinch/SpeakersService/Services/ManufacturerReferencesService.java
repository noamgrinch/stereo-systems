package com.grinch.SpeakersService.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grinch.SpeakersService.BusinessLogic.Entites.ManufacturerReference;
import com.grinch.SpeakersService.Repositories.ManufacturerReferenceRepository;
@Service
public class ManufacturerReferencesService {
	
	@Autowired
	private ManufacturerReferenceRepository repository;

	public ManufacturerReference getManufacturerReference(ManufacturerReference manufacturerReference) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean exists(ManufacturerReference manufacturerReference) {
		return !repository.findByManufacturerId(manufacturerReference.getManufacturerId()).isEmpty();
	}
}
