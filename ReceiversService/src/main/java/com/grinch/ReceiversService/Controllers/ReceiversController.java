package com.grinch.ReceiversService.Controllers;

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
import com.grinch.ReceiversService.BusinessLogic.Entities.Receiver;
import com.grinch.ReceiversService.Services.ReceiversService;

@RestController
@RequestMapping("/Receivers")
public class ReceiversController {
	@Autowired
	private ReceiversService service;

	@GetMapping("/{id}")
	public Receiver getReceiver(@PathVariable("id") Long id) throws Exception {
		return service.getReceiver(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReceiver(@PathVariable("id") Long id) throws Exception {
		service.deleteReceiver(id);
	}
	
	@PostMapping()
	public Receiver postReceiver(@RequestBody @Valid Receiver Receiver) throws Exception {
		return service.postReceiver(Receiver);
	}
	
	@PutMapping()
	public Receiver updateReceiver(@RequestBody @Valid Receiver Receiver) throws Exception {
		return service.putReceiver(Receiver);
	}
}
