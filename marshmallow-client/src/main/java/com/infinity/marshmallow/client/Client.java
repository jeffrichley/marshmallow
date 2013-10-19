package com.infinity.marshmallow.client;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.infinity.marshmallow.communication.ByteMessage;
import com.infinity.marshmallow.communication.MyMessage;

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
//	    	connector.getFilterChain().addLast("codec",
//		            new ProtocolCodecFilter(new TextLineCodecFactory()));
	    	Injector injector = Guice.createInjector(new ClientInjectionModule());
	    	ProtocolCodecFactory pcf = injector.getInstance(ProtocolCodecFactory.class);
	    	connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(pcf));
	    	
			
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
	    for (int i = 1; i <= 10; i++) {
			MyMessage msg = new MyMessage("hello " + i);
	    	session.write(msg);
	    }
//	    session.write(new MyMessage("quit"));
	    ByteMessage byteMessage = new ByteMessage();
	    byteMessage.setBytes("quit".getBytes());
		session.write(byteMessage);
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
	
}
