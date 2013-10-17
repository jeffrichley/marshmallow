package com.infinity.marshmallow.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.sessions.IoSessionWrapper;

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
		// TODO: make getting the wrapper a pool instead of creating objects each time
		IoSessionWrapper sessionWrapper = new IoSessionWrapper(session);
		ClientSession clientSession = clientManager.getSession(sessionWrapper);
		
		codec.processMessage(message, clientSession);
	}
	
}
