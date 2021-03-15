package com.ude.proyecto.logica.entidades;

import java.util.ArrayList;

public class Jugador {

	public static final String AZUL = "AZUL";
	public static final String ROJO = "ROJO";
	private ArrayList<Defensa> artillerias = new ArrayList<Defensa>();
	private ArrayList<Avion> aviones = new ArrayList<Avion>();
	private String bando;
	private Provision depositoCombustible;
	private Provision depositoExplosivos;
	private int idJugador;

	private String nombre;
	private Defensa torreta;

	public Jugador(int idJugador, String nombre, String bando) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.bando = bando;
	}

	public Defensa getArtilleria(int idComponente) {

		for (Defensa artilleria : this.artillerias) {
			if (artilleria.getIdComponente() == idComponente) {
				return artilleria;
			}
		}
		return null;
	}

	public ArrayList<Defensa> getArtillerias() {
		return artillerias;
	}

	public Avion getAvion(int idComponente) {
		for (Avion avion : this.aviones) {
			if (avion.getIdComponente() == idComponente) {
				
				return avion;
			}
		}
		return null;
	}

	public ArrayList<Avion> getAviones() {
		return aviones;
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

	public String getNombre() {
		return nombre;
	}

	public Defensa getTorreta() {
		return torreta;
	}

	public void setArtillerias(ArrayList<Defensa> artillerias) {
		this.artillerias = artillerias;
	}

	public void setAviones(ArrayList<Avion> aviones) {
		this.aviones = aviones;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTorreta(Defensa torreta) {
		this.torreta = torreta;
	}

}
