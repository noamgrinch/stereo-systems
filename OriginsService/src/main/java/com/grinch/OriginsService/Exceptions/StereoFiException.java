package com.grinch.OriginsService.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class StereoFiException extends Exception{/**
	 * 
	 */
	private static final long serialVersionUID = -3900662960873574340L;
	private HttpStatus httpStatusCode;
	
	public StereoFiException(String msg) {
		super(msg);
	}

}
