package com.infinity.marshmallow.communication;


import com.infinity.marshmallow.annotations.CommandHandler;
import com.infinity.marshmallow.api.server.ClientSession;

@CommandHandler(commandId=Byte.MIN_VALUE)
public class TestCommandHandler implements MessageHandler {

	@Override
	public void handleMessage(Message message, ClientSession session) {
		
	}


}
