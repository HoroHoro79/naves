package com.proyecto.naves.exception;

public class ResourceNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7514204884582530974L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}