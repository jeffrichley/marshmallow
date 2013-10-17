package com.infinity.marshmallow.sessions;

import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.ExternalClientWrapper;

public class IoSessionWrapper implements ExternalClientWrapper {

	private final IoSession session;

	public IoSessionWrapper(IoSession session) {
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

}
