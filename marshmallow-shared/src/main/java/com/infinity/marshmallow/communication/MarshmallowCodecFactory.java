package com.infinity.marshmallow.communication;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.google.inject.Inject;

public class MarshmallowCodecFactory implements ProtocolCodecFactory {

	private ProtocolEncoder encoder;
	private ProtocolDecoder decoder;

	@Inject
	public MarshmallowCodecFactory(ProtocolEncoder encoder, ProtocolDecoder decoder) {
		this.encoder = encoder;
		this.decoder = decoder;
	}
	
	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return null;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return null;
	}

}
