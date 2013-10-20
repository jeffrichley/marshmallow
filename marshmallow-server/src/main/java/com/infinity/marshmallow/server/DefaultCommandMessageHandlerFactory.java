package com.infinity.marshmallow.server;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

import com.infinity.marshmallow.annotations.CommandHandler;
import com.infinity.marshmallow.api.exception.MarshmallowException;
import com.infinity.marshmallow.communication.MessageHandler;
import com.infinity.marshmallow.server.impl.CommandMessageHandlerFactory;

/**
 * This class scans the classpath for anything that is registered to handle command messages
 * @author jeff.richley
 */
public class DefaultCommandMessageHandlerFactory implements CommandMessageHandlerFactory {

	private Map<Byte, Class<? extends MessageHandler>> handlers = new HashMap<Byte, Class<? extends MessageHandler>>();
	
	public DefaultCommandMessageHandlerFactory() {
		try {
			URL[] urls = ClasspathUrlFinder.findClassPaths();
			AnnotationDB db = new AnnotationDB();
			db.scanArchives(urls);
			Set<String> handlerClasses = db.getAnnotationIndex().get(CommandHandler.class.getName());
			for (String name : handlerClasses) {
				try {
					@SuppressWarnings("unchecked")
					Class<? extends MessageHandler> handler = (Class<? extends MessageHandler>) Class.forName(name);
					if (!MessageHandler.class.isAssignableFrom(handler)) {
						throw new MarshmallowException("The class " + name + " does not implement MessageHandler");
					}
					CommandHandler annotation = handler.getAnnotation(CommandHandler.class);
					byte id = annotation.commandId();
					
					handlers.put(id, (Class<? extends MessageHandler>) handler);
				} catch (ClassNotFoundException e) {
					throw new MarshmallowException("Unable to create the handler class", e);
				}
			}
		} catch (IOException e) {
			throw new MarshmallowException("Not able to find handlers on the classpath", e);
		}
	}

	public List<Class<? extends MessageHandler>> getRegisteredHandlers() {
		return new ArrayList<Class<? extends MessageHandler>>(handlers.values());
	}

}
