package com.infinity.marshmallow.communication;

import com.infinity.marshmallow.annotations.NetworkObject;

@NetworkObject(messageId=Byte.MIN_VALUE+1)
public class CommandMessage implements Message {

	private byte commandId;
	private byte[] original;
	
	public CommandMessage(){}

	public CommandMessage(byte commandNumber) {
		this.commandId = commandNumber;
	}
	
	@Override
	public byte[] getBytes() {
		byte[] bytes = new byte[original.length+1];
		bytes[0] = commandId;
		for (int i = 0; i < original.length; i++) {
			bytes[i+1] = original[i];
		}
		return bytes;
	}

	@Override
	public void setBytes(byte[] bytes) {
		this.original = bytes;
	}
	
	public byte getCommandId() {
		if (original == null) {
			return commandId;
		}
		return original[0];
	}
	
//	private int sizeCount = 0;
//	private List<Object> parts = new ArrayList<Object>();
//
//	@Override
//	public byte[] getBytes() {
//		ByteBuffer buff = ByteBuffer.allocate(sizeCount);
//		
//		for (Object o : parts) {
//			String s = (String) o;
//			buff.put((byte) s.length());
//			buff.put(s.getBytes());
//		}
//		
//		return buff.array();
//	}
//
//	@Override
//	public void setBytes(byte[] bytes) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void addString(String msg) {
//		parts.add(msg);
//		sizeCount += msg.length() + 1;
//	}
//
//	public void addByteArray(byte[] bytes) {
//		parts.add(bytes);
//	}
//
//	public String getString() {
//		return null;
//	}
//	
//	public void reset() {
//		
//	}
}
