package com.grinch.ManufacturersService.Controllers;

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
import org.springframework.web.context.request.WebRequest;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Services.ManufacturersService;


@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
	
	private Logger logger = LogManager.getLogger(ManufacturersController.class);
	
	@Autowired
	private ManufacturersService service;

	@GetMapping("/{id}")
	public Manufacturer getManufacturer(@PathVariable("id") Long id) throws Exception {
		return service.getManufacturer(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteManufacturer(@PathVariable("id") Long id) throws Exception {
		service.deleteManufacturer(id);
	}
	
	@PostMapping()
	public Manufacturer postManufacturer(@RequestBody @Valid Manufacturer Manufacturer, WebRequest request) throws Exception {
		logger.info("Received post request from " + request.getLocale().toString());
		return service.postManufacturer(Manufacturer);
	}
	
	@PutMapping()
	public Manufacturer updateManufacturer(@RequestBody @Valid Manufacturer Manufacturer) throws Exception {
		return service.putManufacturer(Manufacturer);
	}
}
