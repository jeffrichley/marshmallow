package com.infinity.marshmallow.server.impl;

import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.MessageCodec;

public class DefaultMessageCodec implements MessageCodec {

	@Override
	public void processMessage(Object message, ClientSession session) {
		// TODO Auto-generated method stub
		
		System.out.println(message);
	}

}
