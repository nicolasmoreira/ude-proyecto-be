package com.ude.proyecto.logica.entidades;

public class AvionOLD {

	private int id;
	private float x;
	private float y;

	public AvionOLD(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public AvionOLD(int id, float x, float y) {
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
