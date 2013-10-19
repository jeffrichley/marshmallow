package com.infinity.marshmallow.communication;

import com.infinity.marshmallow.annotations.NetworkObject;

@NetworkObject(messageId=1)
public class ByteMessage implements Message {

	private byte[] bytes = null;
	
	public ByteMessage(){}
	public ByteMessage(byte[] bytes) {
		this.bytes = bytes;
	}
	
	@Override
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
