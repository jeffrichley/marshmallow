package com.infinity.marshmallow.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.MessageCodec;

public class CodecAdapter extends IoHandlerAdapter {

	/** All messages ultimately get funneled into this class to be processed */
	private MessageCodec codec;
	private ClientManager clientManager;
	
	public CodecAdapter(MessageCodec codec, ClientManager clientManager) {
		this.codec = codec;
		this.clientManager = clientManager;
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
