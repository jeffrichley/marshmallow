package com.infinity.marshmallow.api.exception;

/**
 * Base exception in the Marshmallow Server system
 * 
 * @author jeffreyrichley
 */
public class MarshmallowException extends RuntimeException {

	/** Generated serial id */
	private static final long serialVersionUID = -726334094065658180L;

	/**
	 * Base exception of the Marshmallow Server that wraps a <code>Throwable</code> and a message
	 * @param msg The exception description
	 * @param throwable The wrapped throwable
	 */
	public MarshmallowException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	/**
	 * Base exception of the Marshmallow Server with a message
	 * @param msg The exception description
	 */
	public MarshmallowException(String msg) {
		super(msg);
	}

}
