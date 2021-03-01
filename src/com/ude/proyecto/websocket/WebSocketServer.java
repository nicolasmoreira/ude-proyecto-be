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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.Fachada;

@ServerEndpoint(value = "/websocket")
public class WebSocketServer {

	private enum EVENTS {
		AVION_DERRIBADO("avionDerribado"), CARGA_COMBUSTIBLE("cargaCombustible"),
		COLISION_ENTRE_AVIONES("colisionEntreAviones"), DISPARO_AVION("disparoAvion"),
		DISPARO_TORRETA("disparoTorreta"), MOVIMIENTO_AVION("movimientoAvion"), MOVIMIENTO_TORRETA("movimientoTorreta"),
		PARTIDA_DETENIDA("partidaDetenida"), PARTIDA_FINALIZADA("partidaFinalizada"),
		PARTIDA_INICIADA("partidaIniciada"), PARTIDA_REANUDADA("partidaReanudada"), SALIR("salir");

		private String event;

		EVENTS(String event) {
			this.event = event;
		}

		public String getValue() {
			return this.event;
		}
	}

	private static final int MIN_SESSIONS = 2;

	private static final Map<String, Session> sessions = new HashMap<String, Session>();

	private void broadcastAll(String msg) throws IOException {
		for (Entry<String, Session> s : sessions.entrySet()) {
			s.getValue().getBasicRemote().sendText(msg);
		}
	}

	private void broadcastOne(String msg, Session session) throws IOException {
		// System.out.println(msg);
		for (Entry<String, Session> s : sessions.entrySet()) {
			if (s.getValue().getId() != session.getId()) {
				s.getValue().getBasicRemote().sendText(msg);
				// System.out.println("Va bala:" + msg);
			}
		}
	}

	@OnClose
	public void onClose(CloseReason reason, Session session) {
		System.out.println("Cerrando la sessions: " + session.getId() + ", motivo: " + reason.getReasonPhrase());
		System.out.println("Cantidad de sessions: " + sessions.size());
		sessions.remove(session.getId());
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

		String json = message;
		JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
		String event = jsonObject.get("event").getAsString();
		Fachada fachada = Fachada.getInstanceFachada();

		if (EVENTS.MOVIMIENTO_AVION.getValue().equals(event)) {

			JsonObject data = jsonObject.get("data").getAsJsonObject();

			int idJugador = data.get("idJugador").getAsInt();
			int idComponente = data.get("idComponente").getAsInt();
			float ubicacionX = data.get("ubicacionX").getAsFloat();
			float ubicacionY = data.get("ubicacionY").getAsFloat();
			float rotacion = data.get("rotacion").getAsFloat();

//			fachada.setCoordenadaComponente(idJugador, idComponente, Avion.TIPO_AVION, ubicacionX, ubicacionY, rotacion); //creo que explota porque hay dos sessiones
			// System.out.println(data.toString());
			this.broadcastOne(message, session);

			/*
			 * { "idComponente": 1, "tipoComponente": "avion", "ubicacionX": 1,
			 * "ubicacionY": 1, "rotacion": 1, "vida": 100, "sprite": "ASDADASDAD",
			 * "sonido": "ASDADASDAD", "altitudAlta": false, "tieneBomba": false,
			 * "barraCombustible": 100, "rangoDisparo": 100, "enfocado": true }
			 */

		} else if (EVENTS.MOVIMIENTO_TORRETA.getValue().equals(event)) {
			this.broadcastOne(message, session);

		} else if (EVENTS.DISPARO_AVION.getValue().equals(event)) {

		} else if (EVENTS.DISPARO_TORRETA.getValue().equals(event)) {

		} else if (EVENTS.PARTIDA_FINALIZADA.getValue().equals(event)) {

		} else if (EVENTS.PARTIDA_INICIADA.getValue().equals(event)) {

		} else if (EVENTS.PARTIDA_DETENIDA.getValue().equals(event)) {

		} else if (EVENTS.PARTIDA_REANUDADA.getValue().equals(event)) {

		} else if (EVENTS.COLISION_ENTRE_AVIONES.getValue().equals(event)) {

		} else if (EVENTS.CARGA_COMBUSTIBLE.getValue().equals(event)) {
			this.broadcastOne(message, session);

		} else if (EVENTS.AVION_DERRIBADO.getValue().equals(event)) {

		} else if (EVENTS.SALIR.getValue().equals(event)) {

		} else {
			throw new Exception("Event not implemented.");
		}

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