package com.ude.proyecto.logica.entidades;

public class Avion extends Componente {

	private boolean altitudAlta;
	private boolean tieneBomba;
	private float barraCombustible;
	private float rangoDisparo;
	private boolean enfocado;
	private Proyectil bomba;
	private Proyectil municion;

	public Avion(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, int vida, String sprite,
			String sonido, boolean altitudAlta, boolean tieneBomba, float barraCombustible, float rangoDisparo,
			boolean enfocado, Proyectil bomba, Proyectil municion) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, vida, sprite, sonido);
		this.altitudAlta = altitudAlta;
		this.tieneBomba = tieneBomba;
		this.barraCombustible = barraCombustible;
		this.rangoDisparo = rangoDisparo;
		this.enfocado = enfocado;
		this.bomba = bomba;
		this.municion = municion;
	}

	public boolean isAltitudAlta() {
		return altitudAlta;
	}

	public void setAltitudAlta(boolean altitudAlta) {
		this.altitudAlta = altitudAlta;
	}

	public boolean isTieneBomba() {
		return tieneBomba;
	}

	public void setTieneBomba(boolean tieneBomba) {
		this.tieneBomba = tieneBomba;
	}

	public float getBarraCombustible() {
		return barraCombustible;
	}

	public void setBarraCombustible(float barraCombustible) {
		this.barraCombustible = barraCombustible;
	}

	public float getRangoDisparo() {
		return rangoDisparo;
	}

	public void setRangoDisparo(float rangoDisparo) {
		this.rangoDisparo = rangoDisparo;
	}

	public boolean isEnfocado() {
		return enfocado;
	}

	public void setEnfocado(boolean enfocado) {
		this.enfocado = enfocado;
	}

	public Proyectil getBomba() {
		return bomba;
	}

	public void setBomba(Proyectil bomba) {
		this.bomba = bomba;
	}

	public Proyectil getMunicion() {
		return municion;
	}

	public void setMunicion(Proyectil municion) {
		this.municion = municion;
	}

}
