package com.infinity.marshmallow.server;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DefaultCommandMessageHandlerFactoryTest {

	private DefaultCommandMessageHandlerFactory cut;

	@Before
	public void setUp() throws Exception {
		cut = new DefaultCommandMessageHandlerFactory();
	}

	@Test
	public void ensureRegisteredHandlers() {
		assertThat("Must have at least one handler", cut.getRegisteredHandlers().size(), greaterThan(0));
	}

}
