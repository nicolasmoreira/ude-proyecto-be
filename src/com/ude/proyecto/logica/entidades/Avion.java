package com.ude.proyecto.logica.entidades;

public class Avion {

	private int id;
	private float x;
	private float y;

	public Avion(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Avion(int id, float x, float y) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

}
