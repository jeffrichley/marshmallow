package com.infinity.marshmallow.sessions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.ExternalClientWrapper;

public class DefaultClientManager implements ClientManager {
	
	public static final String CLIENT_ID_SESSION_KEY = "clientSessionId";
	
	private final Map<String, ClientSession> sessions = new HashMap<String, ClientSession>();

	@Override
	public ClientSession getSession(ExternalClientWrapper externalClient) {
		ClientSession session = null;
		
		String clientId = externalClient.getAttribute(CLIENT_ID_SESSION_KEY);
		
		// if the clientId is null, they don't have a session so we need to create one
		if (clientId == null) {
			clientId = UUID.randomUUID().toString();
			session = new DefaultClientSession(clientId);
			
			sessions.put(clientId, session);
		} else {
			session = sessions.get(clientId);
		}
		
		return session;
	}


}
