package com.infinity.marshmallow.communication;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class StubbedProtocolEncoderOutput implements ProtocolEncoderOutput {

	private Object written;

	@Override
	public WriteFuture flush() {
		return null;
	}

	@Override
	public void mergeAll() {

	}

	@Override
	public void write(Object written) {
		this.written = written;
	}

	public Object getWritten() {
		return written;
	}

}
