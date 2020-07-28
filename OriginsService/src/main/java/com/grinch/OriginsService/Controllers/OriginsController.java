package com.grinch.OriginsService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grinch.OriginsService.BusinessLogic.Origin;
import com.grinch.OriginsService.Controllers.Services.OriginsService;

@RestController
@RequestMapping("/")
public class OriginsController {
	
	@Autowired
	private OriginsService service;

	@PostMapping("/origins/validate")
	public ResponseEntity<Boolean> validateOrigin(@RequestBody Origin origin) throws Exception{
		return new ResponseEntity<Boolean>(service.validateOrigin(origin),HttpStatus.OK);
	}
}
