package com.infinity.marshmallow.api.server;

/**
 * Wrapper interface to insulate from external libraries
 * @author jeff.richley
 */
public interface ExternalClientWrapper {

	/**
	 * Get the named attribute from the session
	 * @param attributeName The name of the attribute to retrieve
	 * @return The value of the requested attribute
	 */
	String getAttribute(String attributeName);

	/**
	 * Set the attribute associated with a key
	 * @param attributeName The name of the attribute to set
	 * @param value The value to associate with the given name
	 */
	void setAttribute(String attributeName, String value);
}
