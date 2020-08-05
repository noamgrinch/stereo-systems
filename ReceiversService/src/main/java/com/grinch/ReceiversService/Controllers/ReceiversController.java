package com.grinch.ReceiversService.Controllers;

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
import com.grinch.ReceiversService.BusinessLogic.Entities.Receiver;
import com.grinch.ReceiversService.Services.ReceiversService;

@RestController
@RequestMapping("/Receivers")
public class ReceiversController {
	@Autowired
	private ReceiversService service;
	private static Logger logger = LogManager.getLogger(ReceiversController.class);
	
	@GetMapping("/{id}")
	public Receiver getReceiver(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received get request from " + request.getRemoteAddr());
		return service.getReceiver(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReceiver(@PathVariable("id") Long id, HttpServletRequest  request) throws Exception {
		logger.info("Received delete request from " + request.getRemoteAddr());
		service.deleteReceiver(id);
	}
	
	@PostMapping()
	public Receiver postReceiver(@RequestBody @Valid Receiver Receiver, HttpServletRequest  request) throws Exception {
		logger.info("Received post request from " + request.getRemoteAddr());
		return service.postReceiver(Receiver);
	}
	
	@PutMapping()
	public Receiver updateReceiver(@RequestBody @Valid Receiver Receiver, HttpServletRequest  request) throws Exception {
		logger.info("Received put request from " + request.getRemoteAddr());
		return service.putReceiver(Receiver);
	}
}
