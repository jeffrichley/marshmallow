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
import com.infinity.marshmallow.api.exception.MarshmallowException;
import com.infinity.marshmallow.communication.ByteMessage;
import com.infinity.marshmallow.communication.CommandMessage;
import com.infinity.marshmallow.communication.Message;

public class Client {

	private static final long CONNECT_TIMEOUT = 5000;
	private static final boolean USE_CUSTOM_CODEC = false;
	private static final String HOSTNAME = "localhost";
	private static final int PORT = 8000;
	
	private NioSocketConnector connector;
	private IoSession session;

	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		client.startClient();
		
		for (int i = 1; i <= 10; i++) {
			String msg = "hello " + i;
			client.write(msg.getBytes());
	    }
		
		CommandMessage msg = new CommandMessage((byte) 1);
		msg.setBytes("howdy".getBytes());
		client.write(msg);
		
		client.close();
	}

	public void write(byte[] bytes) {
		ByteMessage byteMessage = new ByteMessage();
	    byteMessage.setBytes(bytes);
		session.write(byteMessage);
	}
	
	public void write(Message message) {
		session.write(message);
	}
	
	public void startClient() throws InterruptedException {
		connector = new NioSocketConnector();
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

        try {
            ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
            future.awaitUninterruptibly();
            session = future.getSession();
        } catch (RuntimeIoException e) {
            throw new MarshmallowException("Failed to connect to " + HOSTNAME + ":" + PORT, e);
        }
	}

	public void close() {
		ByteMessage byteMessage = new ByteMessage();
	    byteMessage.setBytes("quit".getBytes());
		
	    write(byteMessage);
		
		session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
	
}
