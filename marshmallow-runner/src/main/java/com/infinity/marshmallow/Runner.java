package com.infinity.marshmallow;

import com.infinity.marshmallow.api.Server;
import com.infinity.marshmallow.api.ServerListener;
import com.infinity.marshmallow.server.DefaultMarshmallowServer;

/**
 * Hello world!
 * 
 */
public class Runner {
	public static void main(String[] args) {
		Server server = new DefaultMarshmallowServer();
		
		server.configureServer();
		server.addListener(new ServerListener(){});
		server.startServer();
	}
}
