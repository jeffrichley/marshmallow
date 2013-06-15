package com.infinity.marshmallow.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.MessageCodec;

public class CodecAdapter extends IoHandlerAdapter {

	private MessageCodec codec;
	
	public CodecAdapter(MessageCodec codec) {
		this.codec = codec;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("message recieved: " + message);
		
		String m = (String) message;
		if ("quit".equals(m)) {
			session.close(false);
		}
	}
	
}
