package com.infinity.marshmallow.communication;

import com.infinity.marshmallow.annotations.NetworkObject;
import com.infinity.marshmallow.api.exception.MarshmallowException;

@NetworkObject(messageId=Byte.MIN_VALUE)
public class MyMessage implements Message {

	private String message;

	public MyMessage() {};
	public MyMessage(String message) throws MarshmallowException {
		if (message == null) {
			throw new MarshmallowException("unable to send a null message");
		}
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public byte[] getBytes() {
		return message.getBytes();
	}

	@Override
	public void setBytes(byte[] bytes) {
		message = new String(bytes);
	}
	
}
