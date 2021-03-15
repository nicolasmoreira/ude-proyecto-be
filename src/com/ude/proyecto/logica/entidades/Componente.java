package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public abstract class Componente {

	public static int idAutonumerico = 0;

	public static final String TIPO_DEFENSA = "DEFENSA";
	public static final String TIPO_PROVISION = "PROVISION";
	protected int idComponente;
	protected String tipoComponente;
	protected float ubicacionX;
	protected float ubicacionY;
	protected float rotacion;
	protected int vida;
	protected String sprite;
	protected String sonido;
	
	protected Componente() {
		super();
	}

	protected Componente(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido) {
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

	protected Componente(String tipoComponente, Properties p) {
		this.idComponente = Componente.idAutonumerico++;
		this.tipoComponente = tipoComponente;

		this.rotacion = Float.parseFloat(p.getProperty(tipoComponente.trim().toLowerCase() + "Rotacion")); // 0;
		this.vida = Integer.parseInt(p.getProperty(tipoComponente.trim().toLowerCase() + "Vida"));// 1;

		int margen = Integer.parseInt(p.getProperty("margen"));// 1;
		int verticeX = Integer.parseInt(p.getProperty("verticeX"));// 1;
		int verticeY = Integer.parseInt(p.getProperty("verticeY"));// 1;

		this.ubicacionX = ubicacionInicial(margen, verticeX);
		this.ubicacionY = ubicacionInicial(margen, verticeY);
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

	public void ubicacionEspejar(int tamanioPantalla, float ubicacionY) {
		this.ubicacionX = tamanioPantalla - this.ubicacionX;
		this.ubicacionY = ubicacionY;
	}

	public float ubicacionInicial(float margen, float veritce) {
		return (int) (Math.random() * margen + veritce);
	}

}
