package com.infinity.marshmallow.communication;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

import com.google.inject.Inject;

public class MarshmallowCodecFactory extends DemuxingProtocolCodecFactory {

	@Inject
	public MarshmallowCodecFactory() {
		addMessageDecoder(new MarshmallowMessageDecoder());
		
		addMessageEncoder(MyMessage.class, MyMessageEncoder.class);
	}
	

}
