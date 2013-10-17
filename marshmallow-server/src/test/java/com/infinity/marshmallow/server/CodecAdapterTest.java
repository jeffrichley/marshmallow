package com.infinity.marshmallow.server;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.mina.core.session.IoSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.ExternalClientWrapper;
import com.infinity.marshmallow.api.server.MessageCodec;

public class CodecAdapterTest {

	private CodecAdapter cut;
	@Mock private MessageCodec codec;
	@Mock private ClientManager clientManager;
	@Mock private ClientSession session;
	@Mock private IoSession iosession;
	
	@Before
	public void setUp() {
		codec = mock(MessageCodec.class);
		clientManager = mock(ClientManager.class);
		session = mock(ClientSession.class);
		iosession = mock(IoSession.class);
		
		cut = new CodecAdapter(codec, clientManager);
		
	}

	@Test
	public void ensureCodecCalled() throws Exception {
		when(clientManager.getSession(any(ExternalClientWrapper.class))).thenReturn(session);
		when(iosession.getAttribute("clientSessionId")).thenReturn("id");

		cut.messageReceived(iosession, "Howdy");
		verify(codec).processMessage("Howdy", session);
	}
	
}
