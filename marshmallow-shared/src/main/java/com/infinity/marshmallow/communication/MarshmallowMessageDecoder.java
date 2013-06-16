package com.infinity.marshmallow.communication;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;

public class MarshmallowMessageDecoder implements MessageDecoder {

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		return OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		IoBuffer buff = in.position(0);
		
		while (buff.hasRemaining()) {
			byte length = buff.get();
			
			byte[] dst = new byte[length];
			
			buff.get(dst, 0, length);
			
			String msg = new String(dst);
			out.write(msg);
		}		
		
		return OK;
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		System.out.println("finished");
	}

}
