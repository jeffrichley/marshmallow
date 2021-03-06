package com.infinity.marshmallow.server.impl;

import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.communication.CommandMessage;
import com.infinity.marshmallow.communication.MessageHandler;

public class DefaultMessageCodec implements MessageCodec {
	
	private CommandMessageHandlerFactory handlerFactory;

	public DefaultMessageCodec(CommandMessageHandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	@Override
	public void processMessage(Object message, ClientSession session) {
		if (message instanceof CommandMessage) {
			CommandMessage command = (CommandMessage) message;
			byte commandId = command.getCommandId();
			MessageHandler handler = handlerFactory.getHandler(commandId);
			handler.handleMessage(command, session);
		}
	}

}
