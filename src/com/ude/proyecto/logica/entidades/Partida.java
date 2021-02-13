package com.ude.proyecto.logica.entidades;

import java.io.InputStream;
import java.util.Properties;

import com.ude.proyecto.logica.colecciones.Jugadores;

public class Partida {

	private int id;
	private Jugadores jugadores;
	private int tamanioEscenarioX;
	private int tamanioEscenarioY;

	public Partida() {
		this.jugadores = new Jugadores();
	}

	public Partida(int id, int tiempo, int pecesRestantes) {

		try {
			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
			p.load(input);

			this.id = id;
			this.jugadores = new Jugadores();
			this.tamanioEscenarioX = Integer.parseInt(p.getProperty("width"));
			this.tamanioEscenarioY = Integer.parseInt(p.getProperty("height"));

		} catch (Exception e) {
			System.out.println("Exception creando partida");
			e.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public Jugadores getJugadores() {
		return jugadores;
	}

	public int getTamanioEscenarioX() {
		return tamanioEscenarioX;
	}

	public int getTamanioEscenarioY() {
		return tamanioEscenarioY;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJugadores(Jugadores jugadores) {
		this.jugadores = jugadores;
	}

	public void setTamanioEscenarioX(int tamanioEscenarioX) {
		this.tamanioEscenarioX = tamanioEscenarioX;
	}

	public void setTamanioEscenarioY(int tamanioEscenarioY) {
		this.tamanioEscenarioY = tamanioEscenarioY;
	}

}
