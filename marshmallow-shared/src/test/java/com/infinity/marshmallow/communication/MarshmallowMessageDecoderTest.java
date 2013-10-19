package com.infinity.marshmallow.communication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.junit.Before;
import org.junit.Test;

public class MarshmallowMessageDecoderTest {

	private MarshmallowMessageDecoder cut;
	private DefaultMessageEncoder encoder;
	private StubbedProtocolEncoderOutput out;
	private StubbedProtocolDecoderOutput finalOut;
	private IoSession session;

	@Before
	public void setUp() throws Exception {
		cut = new MarshmallowMessageDecoder();
		encoder = new DefaultMessageEncoder();
		
		out = new StubbedProtocolEncoderOutput();
		finalOut = new StubbedProtocolDecoderOutput();
		session = mock(IoSession.class);
		
		AbstractMessageHandler.addMapping(MyMessage.class, (byte) -128);
	}

	@Test
	public void ensureDecodesMyMessage() throws Exception {
		Message message = new MyMessage("howdy");
		encoder.encode(session, message, out);
		IoBuffer buff = (IoBuffer) out.getWritten();
		
		cut.decode(session, buff, finalOut);
		
		MyMessage unmarshalled = (MyMessage) finalOut.getWritten();
		
		assertNotNull(unmarshalled);
		assertEquals("howdy", unmarshalled.getMessage());
	}

}
