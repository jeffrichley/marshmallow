package com.infinity.marshmallow.api.server;

/**
 * Keeps track of all user sessions in the system
 * @author jeff.richley
 */
public interface ClientManager {

	/**
	 * Get the named user session.  If there isn't a session associated with the id, make one.
	 * @param externalClient The external client to get a session for
	 * @return The requested session
	 */
	ClientSession getSession(ExternalClientWrapper externalClient);

}
