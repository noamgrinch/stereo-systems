package com.grinch.ManufacturersService.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
@Data
public class ResourceAlreadyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 487817788669332367L;
	private HttpStatus httpStatusCode;
	public ResourceAlreadyExistsException(String msg) {
		super(msg);
		this.setHttpStatusCode(HttpStatus.BAD_REQUEST);
	}

}
