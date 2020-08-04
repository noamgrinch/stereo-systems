package com.grinch.SpeakersService.Exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class StereoFiException extends Exception implements java.io.Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -3900662960873574340L;
	private HttpStatus httpStatusCode;
	
	public StereoFiException(String msg) {
		super(msg);
	}

	public StereoFiException(String msg, HttpStatus notFound) {
		super(msg);
		this.httpStatusCode=notFound;
	}

}
