package com.ude.proyecto.logica.entidades;

import com.ude.proyecto.logica.colecciones.Artillerias;
import com.ude.proyecto.logica.colecciones.Aviones;

public class Jugador {

	private String bando;
	private Provision depositoCombustible;
	private Provision depositoExplosivos;
	private int idJugador;
	private Artillerias artillerias;
	private Aviones aviones;
	private String nombre;
	private Defensa torreta;

	public Jugador(int idJugador, String nombre) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
	}

	public String getBando() {
		return bando;
	}

	public Provision getDepositoCombustible() {
		return depositoCombustible;
	}

	public Provision getDepositoExplosivos() {
		return depositoExplosivos;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public Artillerias getArtillerias() {
		return artillerias;
	}

	public Aviones getAviones() {
		return aviones;
	}

	public String getNombre() {
		return nombre;
	}

	public Defensa getTorreta() {
		return torreta;
	}

	public void setBando(String bando) {
		this.bando = bando;
	}

	public void setDepositoCombustible(Provision depositoCombustible) {
		this.depositoCombustible = depositoCombustible;
	}

	public void setDepositoExplosivos(Provision depositoExplosivos) {
		this.depositoExplosivos = depositoExplosivos;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public void setArtillerias(Artillerias artillerias) {
		this.artillerias = artillerias;
	}

	public void setAviones(Aviones aviones) {
		this.aviones = aviones;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTorreta(Defensa torreta) {
		this.torreta = torreta;
	}

}
