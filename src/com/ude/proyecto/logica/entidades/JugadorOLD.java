package com.ude.proyecto.logica.entidades;

import com.ude.proyecto.logica.colecciones.Aviones;

public class JugadorOLD {

	private Aviones aviones;
	int id;
	private String player;

	public JugadorOLD() {
		aviones = new Aviones();
	}

	public JugadorOLD(int id, String player, Aviones aviones) {
		this.id = id;
		this.player = player;
		this.aviones = aviones;
	}

	public Aviones getAviones() {
		return aviones;
	}

	public int getId() {
		return id;
	}

	public String getPlayer() {
		return player;
	}

	public void setAviones(Aviones aviones) {
		this.aviones = aviones;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + ", " + player;
	}

}
