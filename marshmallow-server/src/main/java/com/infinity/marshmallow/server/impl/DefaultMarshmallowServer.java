package com.infinity.marshmallow.server.impl;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.infinity.marshmallow.api.exception.MarshmallowNetworkException;
import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.api.server.Server;
import com.infinity.marshmallow.server.CodecAdapter;

public class DefaultMarshmallowServer implements Server {

	private static final int PORT = 8000;
	private static final Logger logger = LoggerFactory.getLogger(DefaultMarshmallowServer.class);

	private IoAcceptor acceptor = null;
	private final ProtocolCodecFactory codecFactory;
	private final ClientManager clientManager;

	@Inject
	public DefaultMarshmallowServer(ProtocolCodecFactory codecFactory, ClientManager clientManager) {
		this.codecFactory = codecFactory;
		this.clientManager = clientManager;
	}
	
	@Override
	public void configureServer() {
		logger.debug("Configuring the Default Server");
		
		acceptor = new NioSocketAcceptor();
//		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(codecFactory));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
		logger.debug("Default Server configuration complete");
	}

	@Override
	public void addListener(MessageCodec codec) {
		CodecAdapter codecAdapter = new CodecAdapter(codec, clientManager);
		acceptor.setHandler(codecAdapter);
	}

	@Override
	public void startServer() {
		try {
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			throw new MarshmallowNetworkException("Unable to bind to " + PORT, e);
		}
	}

}
