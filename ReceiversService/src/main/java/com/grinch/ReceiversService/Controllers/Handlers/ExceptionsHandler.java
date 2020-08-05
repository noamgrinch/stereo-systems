package com.grinch.ReceiversService.Controllers.Handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.google.common.base.Throwables;
import com.grinch.ReceiversService.Exceptions.StereoFiException;





@ControllerAdvice
public class ExceptionsHandler {
	
	@Value("${Errors.ExceptionHandler.GeneralMessage}")
	private String GENERAL_ERROR_MESSAGE;
	static final Logger LOGGER = LogManager.getLogger(ExceptionsHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> returnError(Exception e){
		if(e instanceof StereoFiException) {
			return new ResponseEntity<>(e.getMessage(),((StereoFiException) e).getHttpStatusCode());
		}
		LOGGER.error(Throwables.getStackTraceAsString(e));
		return new ResponseEntity<>(GENERAL_ERROR_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
