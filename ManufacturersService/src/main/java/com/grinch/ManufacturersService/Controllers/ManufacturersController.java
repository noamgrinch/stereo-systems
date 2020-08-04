package com.grinch.ManufacturersService.Controllers;

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
import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Services.ManufacturersService;


@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
	
	private Logger logger = LogManager.getLogger(ManufacturersController.class);
	
	@Autowired
	private ManufacturersService service;

	@GetMapping("/{id}")
	public Manufacturer getManufacturer(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received get request from " + request.getRemoteAddr());
		return service.getManufacturer(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteManufacturer(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received delete request from " + request.getRemoteAddr());
		service.deleteManufacturer(id);
	}
	
	@PostMapping()
	public Manufacturer postManufacturer(@RequestBody @Valid Manufacturer Manufacturer, HttpServletRequest  request) throws Exception {
		logger.info("Received post request from " + request.getRemoteAddr());
		return service.postManufacturer(Manufacturer);
	}
	
	@PutMapping()
	public Manufacturer updateManufacturer(@RequestBody @Valid Manufacturer Manufacturer, HttpServletRequest  request) throws Exception {
		logger.info("Received update request from " + request.getRemoteAddr());
		return service.putManufacturer(Manufacturer);
	}
}
