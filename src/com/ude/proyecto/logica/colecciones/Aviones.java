package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.ude.proyecto.logica.entidades.Avion;

public class Aviones {

	private Map<Integer, Avion> diccionario;

	public Aviones() {
		diccionario = new HashMap<Integer, Avion>();
	}

	public Avion get(int id) {
		return diccionario.get(id);
	}

	public JsonArray getASJson() {

		JsonParser jsonParser = new JsonParser();

		return (JsonArray) jsonParser.parse(new Gson().toJson(this.diccionario));
	}

	public void put(Avion v) {
		diccionario.put(v.getId(), v);
	}

}
