package com.infinity.marshmallow.sessions;

import com.infinity.marshmallow.api.server.ClientSession;

public class DefaultClientSession implements ClientSession {

	private String clientId;

	public DefaultClientSession(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String getSessionId() {
		return clientId;
	}

}
