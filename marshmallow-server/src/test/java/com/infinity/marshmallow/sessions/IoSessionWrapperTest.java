package com.infinity.marshmallow.sessions;

import static org.junit.Assert.*;

import org.apache.mina.core.session.DummySession;
import org.apache.mina.core.session.IoSession;
import org.junit.Before;
import org.junit.Test;

public class IoSessionWrapperTest {

	private IoSessionWrapper cut;
	private IoSession session;

	@Before
	public void setUp() throws Exception {
		session = new DummySession();
		cut = new IoSessionWrapper();
		cut.setSession(session);
	}

	@Test
	public void testAttributesWrapped() {
		cut.setAttribute("test", "woot");
		String value = cut.getAttribute("test");
		assertEquals("woot", value);
	}
	
	@Test
	public void testClear() {
		assertNotNull(cut.getSession());
		cut.clear();
		assertNull(cut.getSession());
	}

}
