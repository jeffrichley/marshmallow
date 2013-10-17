package com.infinity.marshmallow.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.api.server.Server;
import com.infinity.marshmallow.server.ServerInjectionModule;

/**
 * Hello world!
 * 
 */
public class Runner {
	
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	private Server server;
	private MessageCodec codec;

	@Inject
	public Runner(Server server, MessageCodec codec) {
		this.server = server;
		this.codec = codec;
	}
	
	public void run() {
		server.configureServer();
		server.addListener(codec);
		server.startServer();
	}
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ServerInjectionModule());
		
		Runner runner = injector.getInstance(Runner.class);
		runner.run();
		
		logger.info("Server ready for client connections");
	}
}
