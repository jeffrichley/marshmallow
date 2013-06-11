package com.infinity.marshmallow.server;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.infinity.marshmallow.api.Server;

public class ServerInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		
		// external dependencies for other projects to use
		bind(Server.class).to(DefaultMarshmallowServer.class).in(Singleton.class);
		
		// server configuration
		
	}

}
