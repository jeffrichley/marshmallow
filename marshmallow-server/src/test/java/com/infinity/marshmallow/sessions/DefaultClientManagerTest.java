package com.infinity.marshmallow.sessions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.ExternalClientWrapper;

public class DefaultClientManagerTest {

	private DefaultClientManager cut;

	@Before
	public void setUp() throws Exception {
		cut = new DefaultClientManager();
	}

	@Test
	public void ensureCreateNewSession() {
		ExternalClientWrapper externalClient = mock(ExternalClientWrapper.class);
		when(externalClient.getAttribute(DefaultClientManager.CLIENT_ID_SESSION_KEY)).thenReturn(null);
		
		ClientSession session = cut.getSession(externalClient);
		
		assertNotNull("Should have created a new sesion", session);
	}

	@Test
	public void ensureRetrievePreviousSession() {
		ExternalClientWrapper externalClient = mock(ExternalClientWrapper.class);
		when(externalClient.getAttribute(DefaultClientManager.CLIENT_ID_SESSION_KEY)).thenReturn(null);
		
		ClientSession session = cut.getSession(externalClient);
		
		when(externalClient
				.getAttribute(DefaultClientManager.CLIENT_ID_SESSION_KEY))
				.thenReturn(session.getSessionId());
		
		ClientSession secondSession = cut.getSession(externalClient);
		
		assertSame(session, secondSession);
	}
}
