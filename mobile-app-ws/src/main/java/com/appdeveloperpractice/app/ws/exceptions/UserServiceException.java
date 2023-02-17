package com.appdeveloperpractice.app.ws.exceptions;

public class UserServiceException extends RuntimeException{

	private static final long serialVersionUID = -4372498290917111589L;

	public UserServiceException(String message) {
		super(message);
	}
}
