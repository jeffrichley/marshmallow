package com.infinity.marshmallow.communication;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for handling messages that are marshalled and unmarshalled
 * @author jeff.richley
 */
public abstract class AbstractMessageHandler {
	
	private static Map<Class<?>, Byte> byteMapping = new HashMap<Class<?>, Byte>();
	private static Map<Byte, Class<?>> classMapping = new HashMap<Byte, Class<?>>();

	/**
	 * Register a mapping with the system.  Messages that are annotated with
	 * <code>NetworkObject</class> will definitely be in here.
	 * @param messageClass The class being registered
	 * @param value The value of the class mappingId
	 */
	public static void addMapping(Class<?> messageClass, byte value) {
		byteMapping.put(messageClass, value);
		classMapping.put(value, messageClass);
	}
	
	/**
	 * Get the mappingId for the given class
	 * @param messageClass The class we are interested in getting the mapping for
	 * @return The mapping for the message class
	 */
	protected static byte getMapping(Class<?> messageClass) {
		return byteMapping.get(messageClass);
	}
	
	/**Get the class that is mapped by the given id
	 * @param messageId The id to retrieve a class for
	 * @return The class that is mapped by the messageId
	 */
	protected static Class<?> getMapping(byte messageId) {
		return classMapping.get(messageId);
	}

}
