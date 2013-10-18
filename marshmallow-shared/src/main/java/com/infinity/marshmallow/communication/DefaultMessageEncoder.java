package com.infinity.marshmallow.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class DefaultMessageEncoder implements MessageEncoder<Message> {

	@Override
	public void encode(IoSession session, Message message, ProtocolEncoderOutput out) throws Exception {
		byte[] bytes = message.getBytes();
		IoBuffer buff = IoBuffer.allocate(bytes.length + 1);
		
		buff.put((byte)(bytes.length));
		buff.put(bytes);
		
		buff.flip();
		
		out.write(buff);
		out.flush();		
	}

}
