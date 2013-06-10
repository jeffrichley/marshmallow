package com.infinity.marshmallow.api.exception;

/**
 * Thrown when networking exceptions are raised
 * @author jeffreyrichley
 */
public class MarshmallowNetworkException extends MarshmallowException {

	/** Generated serial id */
	private static final long serialVersionUID = 4289472870307682905L;

	/**
	 * Network Exception with a message and a wrapped throwable
	 * @param msg The exception message
	 * @param throwable The wrapped <code>Throwable</code>
	 */
	public MarshmallowNetworkException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	/**
	 * A network error with a message
	 * @param msg The exception message
	 */
	public MarshmallowNetworkException(String msg) {
		super(msg);
	}

}
