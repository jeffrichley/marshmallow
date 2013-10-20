package com.infinity.marshmallow.server.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.infinity.marshmallow.communication.TestCommandHandler;

public class DefaultMessageCodecTest {

	private CommandMessageHandlerFactory handlerFactory;
	private DefaultMessageCodec cut;

	@Before
	public void setUp() throws Exception {
		handlerFactory = mock(CommandMessageHandlerFactory.class);
		cut = new DefaultMessageCodec(handlerFactory);
	}

	@Test
	public void test() {
		TestCommandMessage msg = new TestCommandMessage();
//		msg.setBytes(new byte[]{Byte.MIN_VALUE, })
	}

}
