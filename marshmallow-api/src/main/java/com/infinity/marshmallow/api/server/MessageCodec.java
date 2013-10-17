package com.infinity.marshmallow.api.server;

/**
 * Responsible for processing messages coming and going from the server
 * @author jeff.richley
 */
public interface MessageCodec {

	/**
	 * Called when messages come into the server that need to be processed.
	 * @param message The message that was received
	 * @param session The session the message is destined for
	 */
	void processMessage(Object message, ClientSession session);

}
