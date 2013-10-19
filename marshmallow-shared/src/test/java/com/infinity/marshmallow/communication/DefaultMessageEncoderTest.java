package com.infinity.marshmallow.communication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.junit.Before;
import org.junit.Test;

public class DefaultMessageEncoderTest {

	private DefaultMessageEncoder cut;
	private StubbedProtocolEncoderOutput out;
	private IoSession session;
	private Message message;

	@Before
	public void setUp() throws Exception {
		cut = new DefaultMessageEncoder();
		out = new StubbedProtocolEncoderOutput();
		session = mock(IoSession.class);
		
		AbstractMessageHandler.addMapping(ByteMessage.class, (byte) -128);
	}

	@Test
	public void testEncode() throws Exception {
		message = new ByteMessage("howdy".getBytes());
		cut.encode(session, message, out);
		
		IoBuffer buff = (IoBuffer) out.getWritten();
		byte[] array = buff.array();
		
		// check it has the right id
		assertEquals(-128, array[0]);
		
		// check it has the correct payload length
		assertEquals(5, array[1]);
		
		// check it has the correct payload
		byte[] copyOfRange = Arrays.copyOfRange(array, 2, array.length);
		String message = new String(copyOfRange);
		assertEquals("howdy", message);
	}

}
