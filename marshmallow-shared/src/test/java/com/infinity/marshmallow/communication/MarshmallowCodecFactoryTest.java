package com.infinity.marshmallow.communication;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class MarshmallowCodecFactoryTest {

	private MarshmallowCodecFactory cut;

	@Before
	public void setUp() throws Exception {
		cut = new MarshmallowCodecFactory();
	}

	@Test
	public void ensureAnnotationObjectsAreRegistered() throws Exception {
		assertTrue(cut.isRegistered(MyMessage.class));
	}

}
