package com.infinity.marshmallow.server.impl;

import com.infinity.marshmallow.communication.MessageHandler;

public interface CommandMessageHandlerFactory {

	MessageHandler getHandler(byte minValue);

}
