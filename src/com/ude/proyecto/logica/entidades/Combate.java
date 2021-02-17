package com.ude.proyecto.logica.entidades;

import com.ude.proyecto.logica.colecciones.Jugadores;

public class Combate {
	
	private int idCombate;
	private String nombrePartida;
	private Jugadores combateJugadores;
	private String fondoImagen;
	private int tamanioAlto;
	private int tamanioAncho;
	
	public Combate(int idCombate, String nombrePartida, String fondoImagen, int tamanioAlto, int tamanioAncho) {
		super();
		this.idCombate = idCombate;
		this.nombrePartida = nombrePartida;
		this.fondoImagen = fondoImagen;
		this.tamanioAlto = tamanioAlto;
		this.tamanioAncho = tamanioAncho;
	}

	public int getIdCombate() {
		return idCombate;
	}

	public void setIdCombate(int idCombate) {
		this.idCombate = idCombate;
	}

	public String getNombrePartida() {
		return nombrePartida;
	}

	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

	public Jugadores getCombateJugadores() {
		return combateJugadores;
	}

	public void setCombateJugadores(Jugadores combateJugadores) {
		this.combateJugadores = combateJugadores;
	}

	public String getFondoImagen() {
		return fondoImagen;
	}

	public void setFondoImagen(String fondoImagen) {
		this.fondoImagen = fondoImagen;
	}

	public int getTamanioAlto() {
		return tamanioAlto;
	}

	public void setTamanioAlto(int tamanioAlto) {
		this.tamanioAlto = tamanioAlto;
	}

	public int getTamanioAncho() {
		return tamanioAncho;
	}

	public void setTamanioAncho(int tamanioAncho) {
		this.tamanioAncho = tamanioAncho;
	}

}
