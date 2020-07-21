package com.grinch.ReceiversService.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ResourceNotFoundException extends StereoFiException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369504913411717860L;
	private HttpStatus httpStatusCode;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
		this.setHttpStatusCode(HttpStatus.NOT_FOUND);
	}
}
