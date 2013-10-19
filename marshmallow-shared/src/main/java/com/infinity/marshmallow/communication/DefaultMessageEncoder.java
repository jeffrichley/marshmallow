package com.infinity.marshmallow.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class DefaultMessageEncoder extends AbstractMessageHandler implements MessageEncoder<Message> {

	@Override
	public void encode(IoSession session, Message message, ProtocolEncoderOutput out) throws Exception {
		byte[] bytes = message.getBytes();
		IoBuffer buff = IoBuffer.allocate(bytes.length + 2);
		
		// put message type
		buff.put(AbstractMessageHandler.getMapping(message.getClass()));
		
		// put payload length
		buff.put((byte)(bytes.length));
		
		// put payload
		buff.put(bytes);
		
		buff.flip();
		
		out.write(buff);
		out.flush();		
	}

}
