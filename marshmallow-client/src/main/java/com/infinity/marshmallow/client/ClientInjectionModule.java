package com.infinity.marshmallow.client;

import com.google.inject.AbstractModule;
import com.infinity.marshmallow.communication.SharedInjectionModule;

public class ClientInjectionModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new SharedInjectionModule());
	}

}
