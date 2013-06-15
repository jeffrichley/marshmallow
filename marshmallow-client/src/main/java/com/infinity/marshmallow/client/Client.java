package com.infinity.marshmallow.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

	private static final long CONNECT_TIMEOUT = 5000;
	private static final boolean USE_CUSTOM_CODEC = false;
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 8000;

	private static final int CLIENTS_TO_SPAWN = 1;
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < CLIENTS_TO_SPAWN; i++) {
			startClient();
		}
	}

	private static void startClient() throws InterruptedException {
		NioSocketConnector connector = new NioSocketConnector();
	    connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

	    if (USE_CUSTOM_CODEC) {
	    connector.getFilterChain().addLast("codec",
	        new ProtocolCodecFilter(new MyProtocolCodecFactory()));
	    } else {
//	        connector.getFilterChain().addLast("codec",
//	            new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
	    	connector.getFilterChain().addLast("codec",
		            new ProtocolCodecFilter(new TextLineCodecFactory()));
	    }

	    connector.getFilterChain().addLast("logger", new LoggingFilter());
	    connector.setHandler(new ClientSessionHandler());
	    IoSession session;

	    for (;;) {
	        try {
	            ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            break;
	        } catch (RuntimeIoException e) {
	            System.err.println("Failed to connect.");
	            e.printStackTrace();
	            Thread.sleep(5000);
	        }
	    }

	    // wait until the summation is done
	    for (int i = 0; i < 10; i++) {
	    	session.write("hello " + i);
	    }
	    session.write("quit");
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
	
}
