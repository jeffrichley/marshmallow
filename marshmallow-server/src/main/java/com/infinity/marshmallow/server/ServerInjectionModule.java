package com.infinity.marshmallow.server;

import org.apache.mina.filter.codec.ProtocolCodecFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.api.server.Server;
import com.infinity.marshmallow.communication.MarshmallowCodecFactory;
import com.infinity.marshmallow.communication.SharedInjectionModule;
import com.infinity.marshmallow.server.impl.DefaultMarshmallowServer;
import com.infinity.marshmallow.server.impl.DefaultMessageCodec;
import com.infinity.marshmallow.sessions.DefaultClientManager;

public class ServerInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		
		install(new SharedInjectionModule());
		
		// external dependencies for other projects to use
		bind(Server.class).to(DefaultMarshmallowServer.class).in(Singleton.class);
		
		// server configuration
		bind(ProtocolCodecFactory.class).to(MarshmallowCodecFactory.class);
		bind(MessageCodec.class).to(DefaultMessageCodec.class);
		bind(ClientManager.class).to(DefaultClientManager.class);
		
	}

}
