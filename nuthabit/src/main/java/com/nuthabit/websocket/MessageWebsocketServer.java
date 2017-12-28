package com.nuthabit.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nuthabit.dao.Kehu;
import com.nuthabit.model.Message;

@ServerEndpoint(value = "/socket", encoders = { MessageEncoder.class }, decoders = {
		MessageDecoder.class }, configurator = MessageHttpSessionConfigure.class)
public class MessageWebsocketServer {
	Log log = LogFactory.getLog(this.getClass().getName());

	private EndpointConfig config;
	private Session session;
	private String uid;

	private static int onlinecount = 0;
	private static CopyOnWriteArraySet<MessageWebsocketServer> webSocketSet = new CopyOnWriteArraySet<MessageWebsocketServer>();

	public static MessageWebsocketServer getSession(String uid) {
		for (MessageWebsocketServer server : webSocketSet) {
			if (uid.equals(server.uid)) {
				return server;
			}
		}
		return null;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		if (httpSession == null || httpSession.getAttribute("kehu") == null) {
			log.debug("Not an Auth-user , closing websocket");
			try {
				if (session.isOpen()) {
					session.close();
				}
			} catch (IOException e) {
				log.error("Error Closing websocket " + e.getMessage());
			}
		}
		this.config = config;
		this.session = session;
		String kehuId = httpSession.getAttribute("kehu") != null ? ((Kehu) httpSession.getAttribute("kehu")).getKehuId()
				: null;
		if (kehuId == null)
			return;
		this.uid = kehuId;
		webSocketSet.add(this);
		addOnlinecount();
		log.debug("new user " + kehuId + " connected , user online count = " + getOnlinecount());
	}

	@OnClose
	public void onClose(Session session) {
		webSocketSet.remove(this);
		subOnlinecount();
		log.debug("A user disconnected , user online count = " + getOnlinecount());
	}

	@OnMessage
	public void onMessage(Message message, Session session) {
		if (message == null)
			return;
		String toKehuId = message.getTo();
		for (MessageWebsocketServer socket : webSocketSet) {
			if (StringUtils.equals(socket.uid, toKehuId)) {
				try {
					synchronized (socket) {
						socket.session.getBasicRemote().sendText(message.getData());
						log.debug(message.getData());
					}
				} catch (IOException e) {
					log.error("websocket sending message error " + e.getMessage());
					try {
						socket.session.close();
						webSocketSet.remove(socket);
					} catch (IOException e1) {
					}
				}
			}
		}

	}

	public static synchronized void addOnlinecount() {
		MessageWebsocketServer.onlinecount++;
	}

	public static synchronized void subOnlinecount() {
		MessageWebsocketServer.onlinecount--;
	}

	public static synchronized int getOnlinecount() {
		return onlinecount;
	}

	public static HttpSession getHttpSession(EndpointConfig config) {
		if (config == null)
			return null;
		return (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
