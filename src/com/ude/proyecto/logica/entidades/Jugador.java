package com.ude.proyecto.logica.entidades;

import java.util.ArrayList;

public class Jugador {

	public static final String AZUL = "AZUL";
	public static final String ROJO = "ROJO";
	private int idJugador;
	private String nombre;
	private String bando;
	private Provision depositoCombustible;
	private Provision depositoExplosivos;
	private Defensa torreta;
	private ArrayList<Defensa> artillerias = new ArrayList<Defensa>();
	private ArrayList<Avion> aviones = new ArrayList<Avion>();
	
	public Jugador(int idJugador, String nombre, String bando) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.bando = bando;
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
	
	public String getBando() {
		return bando;
	}
	
	public void setBando(String bando) {
		this.bando = bando;
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

	public ArrayList<Defensa> getArtillerias() {
		return artillerias;
	}
	
	public void setArtillerias(ArrayList<Defensa> artillerias) {
		this.artillerias = artillerias;
	}

	public Defensa getArtilleria(int idComponente) {

		for (Defensa artilleria : this.artillerias) {
			if (artilleria.getIdComponente() == idComponente) {
				return artilleria;
			}
		}
		return null;
	}
	
	public ArrayList<Avion> getAviones() {
		return aviones;
	}

	public void setAviones(ArrayList<Avion> aviones) {
		this.aviones = aviones;
	}
	
	public Avion getAvion(int idComponente) {
		for (Avion avion : this.aviones) {
			if (avion.getIdComponente() == idComponente) {
				
				return avion;
			}
		}
		return null;
	}
}
