package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;

/*
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
*/
import com.ude.proyecto.logica.entidades.Avion;

public class Aviones {

	private Map<Integer, Avion> Aviones;

	public Aviones() {
		Aviones = new HashMap<Integer, Avion>();
	}

	public boolean member(int idC) {
		return Aviones.containsKey(idC);
	}

	public void insert(Avion a) {
		int idC = a.getIdComponente();
		Aviones.put(idC, a);
	}

	public Avion find(int idC) {
		return Aviones.get(idC);
	}

	public void delete(int idC) {
		Aviones.remove(idC);
	}
}
