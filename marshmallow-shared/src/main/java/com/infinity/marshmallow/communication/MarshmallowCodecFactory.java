package com.infinity.marshmallow.communication;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

import com.google.inject.Inject;
import com.infinity.marshmallow.annotations.NetworkObject;

public class MarshmallowCodecFactory extends DemuxingProtocolCodecFactory {

	private final Map<Class<?>, Class<? extends MessageEncoder<? extends Message>>> registered = new HashMap<Class<?>, Class<? extends MessageEncoder<? extends Message>>>();
	
	@Inject
	public MarshmallowCodecFactory() throws IOException, ClassNotFoundException {
		addMessageDecoder(new MarshmallowMessageDecoder());
		
		URL[] urls = ClasspathUrlFinder.findClassPaths();
		AnnotationDB db = new AnnotationDB();
		db.scanArchives(urls);
		Set<String> networkObjects = db.getAnnotationIndex().get(NetworkObject.class.getName());
		
		for (String className : networkObjects) {
			Class<?> messageClass = Class.forName(className);
			NetworkObject annotation = messageClass.getAnnotation(NetworkObject.class);
			Class<? extends MessageEncoder<? extends Message>> encoderClass = annotation.encoder();
			
			addMessageEncoder(messageClass, encoderClass);
			registered.put(messageClass, encoderClass);
		}
	}
	
	public boolean isRegistered(Class<?> clazz) {
		return registered.containsKey(clazz);
	}
	

}
