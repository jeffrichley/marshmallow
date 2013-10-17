package com.infinity.marshmallow;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.api.server.Server;
import com.infinity.marshmallow.runner.Runner;

/**
 * Unit test for Runner.
 */
public class RunnerTest {

	private Runner runner;
	@Mock private Server server;
	@Mock private MessageCodec codec;

	@Before
	public void setup() {
		server = mock(Server.class);
		codec = mock(MessageCodec.class);
		runner = new Runner(server, codec);
	}
	
	@Test
	public void testServerCalled() {
		runner.run();
		
		InOrder inorder = inOrder(server);
		inorder.verify(server).configureServer();
		inorder.verify(server).addListener(any(MessageCodec.class));
		inorder.verify(server).startServer();
	}
	
}
