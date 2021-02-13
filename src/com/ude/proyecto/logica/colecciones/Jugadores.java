package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.entidades.Jugador;

public class Jugadores {

	private Map<String, Jugador> diccionario;

	public Jugadores() {
		diccionario = new HashMap<String, Jugador>();
	}

	public int cantidadDeJugadores() {
		return diccionario.size();
	}

	public Jugador get(String nick) {
		return diccionario.get(nick);
	}

	public JsonArray getASJson() {

		JsonArray result = new JsonArray();

		for (Entry<String, Jugador> jugador : diccionario.entrySet()) {
			JsonObject json = new JsonObject();

			json.addProperty("player", this.get(jugador.getKey()).getPlayer());
			json.add("aviones", this.get(jugador.getKey()).getAviones().getASJson());

			result.add(json);

		}

		return result;
	}

	public boolean isEmpty() {
		return diccionario.isEmpty();
	}

	public void put(Jugador j) {
		diccionario.put(j.getPlayer(), j);
	}
}
