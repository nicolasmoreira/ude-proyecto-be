package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;

/*
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
*/
import com.ude.proyecto.logica.entidades.JugadorOLD;

public class JugadoresOLD {

	private Map<String, JugadorOLD> diccionario;

	public JugadoresOLD() {
		diccionario = new HashMap<String, JugadorOLD>();
	}

	public int cantidadDeJugadores() {
		return diccionario.size();
	}

	public JugadorOLD get(String nick) {
		return diccionario.get(nick);
	}
	/*
	public JsonArray getASJson() {

		JsonArray result = new JsonArray();

		for (Entry<String, JugadorOLD> jugador : diccionario.entrySet()) {
			JsonObject json = new JsonObject();

			json.addProperty("player", this.get(jugador.getKey()).getPlayer());
			json.add("aviones", this.get(jugador.getKey()).getAviones().getASJson());

			result.add(json);

		}

		return result;
	}
	*/

	public boolean isEmpty() {
		return diccionario.isEmpty();
	}

	public void put(JugadorOLD j) {
		diccionario.put(j.getPlayer(), j);
	}
}
