package com.hyz.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Controller
//@RequestMapping("/Socket")
@ServerEndpoint(value="/WebSocket")
public class WebScoket {	
	
//	List li = new ArrayList<>();
	private final Logger log = LoggerFactory.getLogger(WebScoket.class);
	private static final AtomicInteger connectedCount= new AtomicInteger(0);
	private static final  Map<String,Object> connectionCount= new ConcurrentHashMap<>();
	private Session session;
	
	public WebScoket() {
		super();
		//nickName="anon"+connectedCount.getAndIncrement();
	}
	@OnOpen
	public void onOpen(Session session,EndpointConfig  config) {
		if(this.session==null) {
			this.session=session;
		}
		String id = session.getId();
		connectionCount.put(id, this);
		System.out.println(session.toString());
		System.out.println(connectionCount.size());
		
	}

	
	@OnMessage
	public void onMessage(String message) throws IOException, InterruptedException {
		Lock lock= new ReentrantLock();
		String ouewId = this.session.getId();
		// 调的是mq传来
		if(StringUtils.isNotBlank(message)) {
			lock.lock();
			try{
				sendMessage(message);
//				session.getAsyncRemote().sendText(message);
			}finally {
				lock.unlock();
			}
		}
	}


	@OnClose
	public void onClose(Session session) {
//		if(this.session!=null) {
//			this.session=null;
//		}
		log.info("websocket connection close");
		System.out.println("Connection closed");
	}
	
	@OnError
	public void onError(Session session,Throwable t) {
		connectionCount.remove(session.getId());
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//t.printStackTrace();
	}
	
	public void sendMessage(String message) {
		Set<Entry<String, Object>> entrySet = connectionCount.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			WebScoket obj = (WebScoket)entry.getValue();
			obj.session.getAsyncRemote().sendText(message);
			
		}
			//session.getAsyncRemote().sendText(message);
	}
	

}
