package com.sti.dao.exception;

public class ErrorException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8576041270652296654L;

	public ErrorException() {
		
	}
	
	public ErrorException(String msg) {
		super(msg);
	}

}
