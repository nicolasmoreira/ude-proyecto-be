package com.ude.proyecto.logica.entidades;

import com.ude.proyecto.logica.colecciones.Artillerias;
import com.ude.proyecto.logica.colecciones.Aviones;

public class Jugador {
	
	private int idJugador;
	private String nombre;
	private Provision depositoCombustible;
	private Provision depositoExplosivos;
	private Defensa torreta;
	private Artillerias JArtillerias;
	private Aviones Javiones;


	public Jugador(int idJugador, String nombre) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provision getDepositoCombustible() {
		return depositoCombustible;
	}

	public void setDepositoCombustible(Provision depositoCombustible) {
		this.depositoCombustible = depositoCombustible;
	}

	public Provision getDepositoExplosivos() {
		return depositoExplosivos;
	}

	public void setDepositoExplosivos(Provision depositoExplosivos) {
		this.depositoExplosivos = depositoExplosivos;
	}

	public Defensa getTorreta() {
		return torreta;
	}

	public void setTorreta(Defensa torreta) {
		this.torreta = torreta;
	}

	public Artillerias getJArtillerias() {
		return JArtillerias;
	}

	public void setJArtillerias(Artillerias jArtillerias) {
		JArtillerias = jArtillerias;
	}

	public Aviones getJaviones() {
		return Javiones;
	}

	public void setJaviones(Aviones javiones) {
		Javiones = javiones;
	}
	
	

	
}
