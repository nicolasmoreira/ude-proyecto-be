package com.ude.proyecto.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;

@ServerEndpoint(value = "/websocket")
public class WebSocketServer {

	private static final Map<String, Session> sessions = new HashMap<String, Session>();	

	private static void broadcastAll(String msg) throws IOException {
		for (Entry<String, Session> s : sessions.entrySet()) {
			s.getValue().getBasicRemote().sendText(msg);
		}
	}

	private static void broadcastOne(String msg, Session session) throws IOException {
		for (Entry<String, Session> s : sessions.entrySet()) {
			if (s.getValue().getId() != session.getId()) {
				s.getValue().getBasicRemote().sendText(msg);
			}
		}
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Cerrando la sessions: " + session.getId() + ", motivo: " + reason.getReasonPhrase());
		System.out.println("Cantidad de sessions: " + sessions.size());
	}

	@OnError
	public void onError(Throwable t) throws Throwable {
		// Most likely cause is a user closing their browser. Check to see if
		// the root cause is EOF and if it is ignore it.
		// Protect against infinite loops.
		int count = 0;
		Throwable root = t;
		while (root.getCause() != null && count < 20) {
			root = root.getCause();
			count++;
		}
		if (root instanceof EOFException) {
			// Assume this is triggered by the user closing their browser and
			// ignore it.
		} else {
			throw t;
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		if ("test".equals(message)) {
			broadcastOne(message, session);
		}
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {

		sessions.put(session.getId(), session);

		System.out.println("Conexion abierta con id: " + session.getId());
		System.out.println("Cantidad de sessions: " + sessions.size());

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("event", "start");

		if (sessions.size() == 2) {
			broadcastAll(jsonObject.toString());
		}

	}
}