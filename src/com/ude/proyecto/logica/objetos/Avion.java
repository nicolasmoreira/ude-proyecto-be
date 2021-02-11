package com.ude.proyecto.logica.objetos;

public class Avion {

	private String codigo;
	private boolean colision;
	private float coordX;
	private float coordY;

	public Avion(float coordX2, float coordY2) {
		super();
		this.coordX = coordX2;
		this.coordY = coordY2;
		this.colision = false;
	}

	public Avion(String codigo, float coordX2, float coordY2, boolean colision) {
		super();
		this.codigo = codigo;
		this.coordX = coordX2;
		this.coordY = coordY2;
		this.colision = colision;
	}

	public float getCoordX() {
		return coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	public boolean hayColision() {
		return colision;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
