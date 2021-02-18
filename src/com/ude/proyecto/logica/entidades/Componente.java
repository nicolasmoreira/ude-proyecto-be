package com.ude.proyecto.logica.entidades;

public abstract class Componente {
	private int idComponente;
	private String tipoComponente;
	private float ubicacionX;
	private float ubicacionY;
	private float rotacion;
	private int vida;
	private String sprite;
	private String sonido;

	public Componente(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY,float rotacion, int vida,
			String sprite, String sonido) {
		super();
		this.idComponente = idComponente;
		this.tipoComponente = tipoComponente;
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.rotacion = rotacion;
		this.vida = vida;
		this.sprite = sprite;
		this.sonido = sonido;
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public String getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public float getUbicacionX() {
		return ubicacionX;
	}

	public void setUbicacionX(float ubicacionX) {
		this.ubicacionX = ubicacionX;
	}

	public float getUbicacionY() {
		return ubicacionY;
	}

	public void setUbicacionY(float ubicacionY) {
		this.ubicacionY = ubicacionY;
	}
	
	public float getRotacion() {
		return rotacion;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}
}
