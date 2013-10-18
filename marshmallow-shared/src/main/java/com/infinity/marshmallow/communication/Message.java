package com.infinity.marshmallow.communication;

/**
 * Represents a new message that needs to be sent back and forth
 * between the server and client
 * @author jeff.richley
 */
public interface Message {

	/**
	 * Get all the bytes that represent this message
	 * @return The bytes that represent this message
	 */
	byte[] getBytes();

}
