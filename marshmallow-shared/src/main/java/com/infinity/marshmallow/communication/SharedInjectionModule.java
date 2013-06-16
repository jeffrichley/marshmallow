package com.infinity.marshmallow.communication;

import org.apache.mina.filter.codec.ProtocolCodecFactory;

import com.google.inject.AbstractModule;

public class SharedInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProtocolCodecFactory.class).to(MarshmallowCodecFactory.class);
	}

}
