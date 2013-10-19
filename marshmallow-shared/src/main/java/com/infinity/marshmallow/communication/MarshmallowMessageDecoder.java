package com.infinity.marshmallow.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public class MarshmallowMessageDecoder extends AbstractMessageHandler implements MessageDecoder {

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		return OK;
	}

	@Override
	@SuppressWarnings("unchecked")
	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		IoBuffer buff = in.position(0);
		
		while (buff.hasRemaining()) {
			byte messageTypeId = buff.get();
			byte length = buff.get();
			byte[] dst = new byte[length];
			buff.get(dst, 0, length);
			
			Class<? extends Message> c = (Class<? extends Message>) getMapping(messageTypeId);
			Message message = c.newInstance();
			message.setBytes(dst);
			
			out.write(message);
		}
		
		return OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		System.out.println("finishDecode...");
	}

}
