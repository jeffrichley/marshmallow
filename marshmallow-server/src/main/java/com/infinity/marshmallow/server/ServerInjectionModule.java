package com.infinity.marshmallow.server;

import org.apache.mina.filter.codec.ProtocolCodecFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.infinity.marshmallow.api.server.Server;
import com.infinity.marshmallow.communication.MarshmallowCodecFactory;
import com.infinity.marshmallow.server.impl.DefaultMarshmallowServer;

public class ServerInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		
		// external dependencies for other projects to use
		bind(Server.class).to(DefaultMarshmallowServer.class).in(Singleton.class);
		
		// server configuration
		bind(ProtocolCodecFactory.class).to(MarshmallowCodecFactory.class);
	}

}
