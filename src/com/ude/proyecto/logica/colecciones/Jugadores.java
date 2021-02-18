package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;

import com.ude.proyecto.logica.entidades.Jugador;

public class Jugadores {

	private Map<Integer, Jugador> Jugadores;

	public Jugadores() {
		Jugadores = new HashMap<Integer, Jugador>();
	}

	public boolean member(int idC) {
		return Jugadores.containsKey(idC);
	}

	public void insert(Jugador a) {
		int idJ = a.getIdJugador();
		Jugadores.put(idJ, a);
	}

	public Jugador find(int idJ) {
		return Jugadores.get(idJ);
	}

}
