package com.infinity.marshmallow.api.server;

/**
 * <p>
 * Interface for defining a Marshmallow Server component.  Once started, this will allow
 * clients to connect and communicate with the system.
 * </p>
 * <p>
 * The fire-up sequence for a server is:
 * </p>
 * <ol>
 * <li>configureServer</li>
 * <li>addListener - as many times as there are listeners</li>
 * <li>startServer</li>
 * </ol> 
 * @author jeffreyrichley
 */
public interface Server {
	
	/**
	 * Setup the server with all configuration
	 */
	void configureServer();
	
	/**
	 * Add a listener to the server to give information to rest of the system to process
	 * @param codec - A listener that will be given information as it is received from the client
	 */
	void addListener(MessageCodec codec);
	
	/**
	 * Fire up the server and begin listening 
	 */
	void startServer();
	
}
