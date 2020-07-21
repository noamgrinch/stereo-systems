package com.grinch.ElementsService.Controllers;

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

import com.grinch.ElementsService.BusinessLogic.Entites.Element;
import com.grinch.ElementsService.Services.ElementsService;



@RestController
@RequestMapping("/Elements")
public class ElementsController {
	
	@Autowired
	private ElementsService service;

	@GetMapping("/{id}")
	public Element getElement(@PathVariable("id") Long id) throws Exception {
		return service.getElement(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteElement(@PathVariable("id") Long id) throws Exception {
		service.deleteElement(id);
	}
	
	@PostMapping()
	public Element postElement(@RequestBody @Valid Element Element) throws Exception {
		return service.postElement(Element);
	}
	
	@PutMapping()
	public Element updateElement(@RequestBody @Valid Element Element) throws Exception {
		return service.putElement(Element);
	}

}
