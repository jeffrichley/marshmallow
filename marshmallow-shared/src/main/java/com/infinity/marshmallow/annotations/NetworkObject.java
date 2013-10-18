package com.infinity.marshmallow.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.infinity.marshmallow.communication.DefaultMessageEncoder;
import com.infinity.marshmallow.communication.Message;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NetworkObject {
	
	Class<? extends MessageEncoder<? extends Message>> encoder() default DefaultMessageEncoder.class;
	
}
