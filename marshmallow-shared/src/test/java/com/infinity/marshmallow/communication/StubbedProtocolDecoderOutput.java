package com.infinity.marshmallow.communication;

import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class StubbedProtocolDecoderOutput implements ProtocolDecoderOutput {

	private Object written;

	@Override
	public void flush(NextFilter arg0, IoSession arg1) {
		
	}

	@Override
	public void write(Object written) {
		this.written = written;
	}

	public Object getWritten() {
		return written;
	}

}
