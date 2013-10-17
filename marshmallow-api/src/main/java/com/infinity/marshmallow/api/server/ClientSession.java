package com.infinity.marshmallow.api.server;

/**
 * Represents a client and any information that needs to be saved for them
 * 
 * @author jeff.richley
 */
public interface ClientSession {

	/**
	 * Get the session id of a client
	 * @return The session id
	 */
	String getSessionId();

}
