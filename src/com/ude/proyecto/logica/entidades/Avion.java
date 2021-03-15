package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Avion extends Componente {

	public static final String TIPO_AVION = "AVION";
	private boolean altitudAlta;
	private boolean tieneBomba;
	private float barraCombustible;
	private float rangoDisparo;
	private boolean enfocado;
	private String spritesLaterales;

	public Avion() {
		super();
	}

	public Avion(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion, int vida,
			String sprite, String sonido, boolean altitudAlta, boolean tieneBomba, float barraCombustible,
			float rangoDisparo, boolean enfocado, String spritesLaterales) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.altitudAlta = altitudAlta;
		this.tieneBomba = tieneBomba;
		this.barraCombustible = barraCombustible;
		this.rangoDisparo = rangoDisparo;
		this.enfocado = enfocado;
		this.spritesLaterales = spritesLaterales;
	}

	public Avion(Properties p) {
		super(TIPO_AVION, p);
		this.altitudAlta = Boolean.getBoolean(p.getProperty("altitudAlta"));
		this.tieneBomba = Boolean.getBoolean(p.getProperty("tieneBomba"));
		this.barraCombustible = Float.parseFloat(p.getProperty("barraCombustible"));
		this.rangoDisparo = Float.parseFloat(p.getProperty("rangoDisparoAvion"));
		this.enfocado = Boolean.getBoolean(p.getProperty("enfocado"));
		this.setSprite(p.getProperty("SpAvion"));
		this.setSonido(p.getProperty("SdAvion"));
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

	public String getSpritesLaterales() {
		return spritesLaterales;
	}

	public void setSpritesLaterales(String spritesLaterales) {
		this.spritesLaterales = spritesLaterales;
	}
}