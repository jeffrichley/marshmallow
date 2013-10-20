package com.infinity.marshmallow.server.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.communication.MessageHandler;
import com.infinity.marshmallow.communication.TestCommandMessage;

public class DefaultMessageCodecTest {

	private CommandMessageHandlerFactory handlerFactory;
	private DefaultMessageCodec cut;
	private ClientSession session;
	private MessageHandler msgHandler;

	@Before
	public void setUp() throws Exception {
		handlerFactory = mock(CommandMessageHandlerFactory.class);
		session = mock(ClientSession.class);
		msgHandler = mock(MessageHandler.class);
		cut = new DefaultMessageCodec(handlerFactory);
	}

	@Test
	public void test() {
		when(handlerFactory.getHandler(Byte.MIN_VALUE)).thenReturn(msgHandler);
		
		TestCommandMessage msg = new TestCommandMessage();
		msg.setBytes(new byte[]{Byte.MIN_VALUE, "a".getBytes()[0]});
		
		cut.processMessage(msg, session);
		
		verify(msgHandler).handleMessage(msg, session);
	}

}
