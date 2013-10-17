package com.infinity.marshmallow.sessions;

import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.ExternalClientWrapper;

public class IoSessionWrapper implements ExternalClientWrapper {

	private IoSession session;

	public void setSession(IoSession session) {
		this.session = session;
	}

	@Override
	public String getAttribute(String attributeName) {
		return (String) session.getAttribute(attributeName);
	}

	@Override
	public void setAttribute(String attributeName, String value) {
		session.setAttribute(attributeName, value);
	}

	protected IoSession getSession() {
		return session;
	}
	
	public void clear() {
		session = null;
	}

}
