package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Avion extends Componente {

	private boolean altitudAlta;
	private float barraCombustible;
	private Proyectil bomba;
	private boolean enfocado;
	private Proyectil municion;
	private float rangoDisparo;
	private String spritesLaterales;
	private boolean tieneBomba;
	public static final String TIPO_AVION = "AVION";
	
	public Avion() {
		super();
	}

	public Avion(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion, int vida,
			String sprite, String sonido, boolean altitudAlta, boolean tieneBomba, float barraCombustible,
			float rangoDisparo, boolean enfocado, Proyectil bomba, Proyectil municion, String spritesLaterales) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.altitudAlta = altitudAlta;
		this.tieneBomba = tieneBomba;
		this.barraCombustible = barraCombustible;
		this.rangoDisparo = rangoDisparo;
		this.enfocado = enfocado;
		this.bomba = bomba;
		this.municion = municion;
		this.spritesLaterales = spritesLaterales;
	}

	// creador inicial desde properties
	public Avion(Properties p) {
		super("Avion", p);
		this.altitudAlta = Boolean.getBoolean(p.getProperty("altitudAlta"));
		this.tieneBomba = Boolean.getBoolean(p.getProperty("tieneBomba"));
		this.barraCombustible = Float.parseFloat(p.getProperty("barraCombustible"));
		this.rangoDisparo = Float.parseFloat(p.getProperty("rangoDisparoAvion")); // este es un multiplo del tamanio del
																					// avion
		this.enfocado = Boolean.getBoolean(p.getProperty("enfocado"));
		this.bomba = null;
		this.municion = null;
		this.setSprite(p.getProperty("SpAvion"));
		this.setSonido(p.getProperty("SdAvion"));
	}

	public float getBarraCombustible() {
		return barraCombustible;
	}

	public Proyectil getBomba() {
		return bomba;
	}

	public Proyectil getMunicion() {
		return municion;
	}

	public float getRangoDisparo() {
		return rangoDisparo;
	}

	public String getSpritesLaterales() {
		return spritesLaterales;
	}

	public boolean isAltitudAlta() {
		return altitudAlta;
	}

	public boolean isEnfocado() {
		return enfocado;
	}

	public boolean isTieneBomba() {
		return tieneBomba;
	}

	public void setAltitudAlta(boolean altitudAlta) {
		this.altitudAlta = altitudAlta;
	}

	public void setBarraCombustible(float barraCombustible) {
		this.barraCombustible = barraCombustible;
	}

	public void setBomba(Proyectil bomba) {
		this.bomba = bomba;
	}

	public void setEnfocado(boolean enfocado) {
		this.enfocado = enfocado;
	}

	public void setMunicion(Proyectil municion) {
		this.municion = municion;
	}

	public void setRangoDisparo(float rangoDisparo) {
		this.rangoDisparo = rangoDisparo;
	}

	public void setSpritesLaterales(String spritesLaterales) {
		this.spritesLaterales = spritesLaterales;
	}

	public void setTieneBomba(boolean tieneBomba) {
		this.tieneBomba = tieneBomba;
	}

}
