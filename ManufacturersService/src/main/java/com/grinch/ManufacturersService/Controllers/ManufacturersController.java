package com.grinch.ManufacturersService.Controllers;

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

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;
import com.grinch.ManufacturersService.Services.ManufacturersService;


@RestController
@RequestMapping("/manufacturers")
public class ManufacturersController {
	
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
	public Manufacturer postManufacturer(@RequestBody @Valid Manufacturer Manufacturer) throws Exception {
		return service.postManufacturer(Manufacturer);
	}
	
	@PutMapping()
	public Manufacturer updateManufacturer(@RequestBody @Valid Manufacturer Manufacturer) throws Exception {
		return service.putManufacturer(Manufacturer);
	}
}
