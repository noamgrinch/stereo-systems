package com.grinch.ManufacturersService.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ResourceNotFoundException extends Exception{

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