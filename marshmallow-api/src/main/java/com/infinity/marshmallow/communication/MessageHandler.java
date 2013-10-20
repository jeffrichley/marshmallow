package com.infinity.marshmallow.communication;

import com.infinity.marshmallow.api.server.ClientSession;

/**
 * Responsible for handling a particular message that was received
 * @author jeff.richley
 */
public interface MessageHandler {

	/**
	 *  Perform some type of operation with the message
	 * @param message 
	 * @param session
	 */
	void handleMessage(Message message, ClientSession session);
	
}
