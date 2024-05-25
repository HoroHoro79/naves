package com.proyecto.naves.exception;


public class ServiceException extends RuntimeException{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 4426364480169491888L;

	public ServiceException(String message) {
	        super(message);
	    }

	    public ServiceException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
