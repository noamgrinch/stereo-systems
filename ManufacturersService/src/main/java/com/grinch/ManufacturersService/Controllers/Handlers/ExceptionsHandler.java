package com.grinch.ManufacturersService.Controllers.Handlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.grinch.ManufacturersService.Exceptions.StereoFiException;


@ControllerAdvice
public class ExceptionsHandler {
	
	@Value("${Errors.ExceptionHandler.GeneralMessage}")
	private String GENERAL_ERROR_MESSAGE;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> returnError(Exception e){
		if(e instanceof StereoFiException) {
			return new ResponseEntity<>(e.getMessage(),((StereoFiException) e).getHttpStatusCode());
		}
		return new ResponseEntity<>(GENERAL_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
