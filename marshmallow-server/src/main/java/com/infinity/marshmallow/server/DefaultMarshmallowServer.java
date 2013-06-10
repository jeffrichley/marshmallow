package com.infinity.marshmallow.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.infinity.marshmallow.api.Server;
import com.infinity.marshmallow.api.ServerListener;
import com.infinity.marshmallow.api.exception.MarshmallowNetworkException;

public class DefaultMarshmallowServer implements Server {

	private static final int PORT = 8000;

	private IoAcceptor acceptor = null;

	@Override
	public void configureServer() {
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}

	@Override
	public void addListener(ServerListener listener) {
		acceptor.setHandler(new MarshmallowServerHandler());
	}

	@Override
	public void startServer() {
		try {
			acceptor.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			throw new MarshmallowNetworkException("Unable to bind to " + PORT,
					e);
		}
	}

}