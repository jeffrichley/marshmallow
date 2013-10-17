package com.infinity.marshmallow.server;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.infinity.marshmallow.api.server.ClientManager;
import com.infinity.marshmallow.api.server.ClientSession;
import com.infinity.marshmallow.api.server.MessageCodec;
import com.infinity.marshmallow.sessions.IoSessionWrapper;

public class CodecAdapter extends IoHandlerAdapter {

	/** All messages ultimately get funneled into this class to be processed */
	private final MessageCodec codec;
	
	/** We can get all sessions from this*/
	private final ClientManager clientManager;
	
	/** Pool to get IoSessionWrappers from */
	private final StackObjectPool<IoSessionWrapper> pool;
	
	public CodecAdapter(MessageCodec codec, ClientManager clientManager) {
		this.codec = codec;
		this.clientManager = clientManager;
		
		IoSessionWrapperFactory factory = new IoSessionWrapperFactory();
		pool = new StackObjectPool<IoSessionWrapper>(factory);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// get out of the pool, don't forget to give it back!
		IoSessionWrapper sessionWrapper = pool.borrowObject();
		sessionWrapper.setSession(session);
		
		ClientSession clientSession = clientManager.getSession(sessionWrapper);
		
		codec.processMessage(message, clientSession);
		
		// give it back like a good citizen
		pool.returnObject(sessionWrapper);
	}
	
	/**
	 * Pool the <code>IoSessionWrapper</code> to keep the load down.  Don't forget to kill
	 * the session when the object is returned to the pool.
	 * @author jeff.richley
	 */
	private class IoSessionWrapperFactory extends BasePoolableObjectFactory<IoSessionWrapper> {
		
		@Override
		public void passivateObject(IoSessionWrapper wrapper) throws Exception {
			wrapper.clear();
		}

		@Override
		public IoSessionWrapper makeObject() throws Exception {
			return new IoSessionWrapper();
		}
		
	}
}
