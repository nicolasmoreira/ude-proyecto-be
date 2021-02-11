package com.ude.proyecto.logica.vo;

import java.io.Serializable;

public class VOAvion implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean colision;
	private float coordX;
	private float coordY;

	public VOAvion(float coordX, float coordY, boolean colision) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.colision = colision;
	}

	public float getCoordX() {
		return coordX;
	}

	public float getCoordY() {
		return coordY;
	}

	public boolean isColision() {
		return colision;
	}

	@Override
	public String toString() {
		return "coordX: " + coordX + " coordY: " + coordY + " colision: " + colision;
	}
}
