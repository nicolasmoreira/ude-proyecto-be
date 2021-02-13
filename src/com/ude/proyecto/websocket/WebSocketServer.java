package com.ude.proyecto.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
	
	private static final int MIN_SESSIONS = 2;

	private enum EVENTS {
		MOVIMIENTO_AVION("movimientoAvion"), 
		MOVIMIENTO_TORRETA("movimientoTorreta"), 
		DISPARO_AVION("disparoAvion"),
		DISPARO_TORRETA("disparoTorreta"), 
		PARTIDA_FINALIZADA("partidaFinalizada"), 
		PARTIDA_INICIADA("partidaIniciada"),
		PARTIDA_DETENIDA("partidaDetenida"), 
		PARTIDA_REANUDADA("partidaReanudada"),
		COLISION_ENTRE_AVIONES("colisionEntreAviones"), 
		CARGA_COMBUSTIBLE("cargaCombustible"),
		AVION_DERRIBADO("avionDerribado"), 
		SALIR("salir");

		private String event;

		EVENTS(String event) {
			this.event = event;
		}

		public String getValue() {
			return this.event;
		}
	}

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
	public void onMessage(String message, Session session) throws Exception {
		if (EVENTS.MOVIMIENTO_AVION.equals(message)) {		
			
		} else if(EVENTS.MOVIMIENTO_TORRETA.equals(message)) {
			
		} else if(EVENTS.DISPARO_AVION.equals(message)) {
			
		} else if(EVENTS.DISPARO_TORRETA.equals(message)) {
			
		} else if(EVENTS.PARTIDA_FINALIZADA.equals(message)) {
			
		} else if(EVENTS.PARTIDA_INICIADA.equals(message)) {
			
		} else if(EVENTS.PARTIDA_DETENIDA.equals(message)) {
			
		} else if(EVENTS.PARTIDA_REANUDADA.equals(message)) {
			
		} else if(EVENTS.COLISION_ENTRE_AVIONES.equals(message)) {
			
		} else if(EVENTS.CARGA_COMBUSTIBLE.equals(message)) {
			
		} else if(EVENTS.AVION_DERRIBADO.equals(message)) {
			
		} else if(EVENTS.SALIR.equals(message)) {
			
		} else {
			throw new Exception("Event not implemented.");
		}
		
		broadcastAll(message);
	}

	@OnOpen
	public void onOpen(Session session) throws IOException {
		sessions.put(session.getId(), session);

		System.out.println("Conexion abierta con id: " + session.getId());
		System.out.println("Cantidad de sessions: " + sessions.size());

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("event", EVENTS.PARTIDA_INICIADA.getValue());

		if (sessions.size() == MIN_SESSIONS) {
			broadcastAll(jsonObject.toString());
		}
	}
}