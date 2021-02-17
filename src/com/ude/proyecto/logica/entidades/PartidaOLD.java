package com.ude.proyecto.logica.entidades;

import java.io.InputStream;
import java.util.Properties;

import com.ude.proyecto.logica.colecciones.JugadoresOLD;

public class PartidaOLD {

	private int id;
	private JugadoresOLD jugadores;
	private int tamanioEscenarioX;
	private int tamanioEscenarioY;

	public PartidaOLD() {
		this.jugadores = new JugadoresOLD();
	}

	public PartidaOLD(int id, int tiempo, int pecesRestantes) {

		try {
			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
			p.load(input);

			this.id = id;
			this.jugadores = new JugadoresOLD();
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

	public JugadoresOLD getJugadores() {
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

	public void setJugadores(JugadoresOLD jugadores) {
		this.jugadores = jugadores;
	}

	public void setTamanioEscenarioX(int tamanioEscenarioX) {
		this.tamanioEscenarioX = tamanioEscenarioX;
	}

	public void setTamanioEscenarioY(int tamanioEscenarioY) {
		this.tamanioEscenarioY = tamanioEscenarioY;
	}

}
