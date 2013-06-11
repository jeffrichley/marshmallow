package com.infinity.marshmallow;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.infinity.marshmallow.api.Server;
import com.infinity.marshmallow.api.ServerListener;
import com.infinity.marshmallow.runner.Runner;

/**
 * Unit test for Runner.
 */
public class RunnerTest {

	private Runner runner;
	private Server server;

	@Before
	public void setup() {
		server = mock(Server.class);
		runner = new Runner(server);
	}
	
	@Test
	public void testServerCalled() {
		runner.run();
		
		InOrder inorder = inOrder(server);
		inorder.verify(server).configureServer();
		inorder.verify(server).addListener(any(ServerListener.class));
		inorder.verify(server).startServer();
	}
	
}
