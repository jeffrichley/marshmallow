package com.infinity.marshmallow.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class MyMessageEncoder implements MessageEncoder<MyMessage> {

	@Override
	public void encode(IoSession session, MyMessage message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buff = IoBuffer.allocate(message.getMessage().length() + 1);
		
		buff.put((byte)(message.getMessage().length()));
		buff.put(message.getMessage().getBytes());
		
		buff.flip();
		
		out.write(buff);
		out.flush();
	}

}
