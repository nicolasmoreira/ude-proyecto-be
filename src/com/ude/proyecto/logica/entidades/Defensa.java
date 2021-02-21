package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Defensa extends Componente {

	private float demoraDesplazamiento;
	private Proyectil municion;
	private float rangoDisparo;
	private String tipoDefensa;

	public Defensa() {
		super();
	}

	public Defensa(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido, String tipoDefensa, float rangoDisparo, float demoraDesplazamiento,
			Proyectil municion) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.tipoDefensa = tipoDefensa;
		this.rangoDisparo = rangoDisparo;
		this.demoraDesplazamiento = demoraDesplazamiento;
		this.municion = municion;
	}

	public Defensa(String tipoDefensa, Properties p) {
		super("Defensa", p);
		this.tipoDefensa = tipoDefensa;
		// Torreta y Artilleria

		this.rangoDisparo = Float.parseFloat(p.getProperty("rangDisp" + tipoDefensa.trim()));
		this.demoraDesplazamiento = Float.parseFloat(p.getProperty("demDesp" + tipoDefensa.trim()));
		this.setSprite(p.getProperty("Sp" + tipoDefensa.trim()));
		this.setSonido(p.getProperty("Sd" + tipoDefensa.trim()));
		this.municion = null;
	}

	public float getDemoraDesplazamiento() {
		return demoraDesplazamiento;
	}

	public Proyectil getMunicion() {
		return municion;
	}

	public float getRangoDisparo() {
		return rangoDisparo;
	}

	public String getTipoDefensa() {
		return tipoDefensa;
	}

	public void setDemoraDesplazamiento(float demoraDesplazamiento) {
		this.demoraDesplazamiento = demoraDesplazamiento;
	}

	public void setMunicion(Proyectil municion) {
		this.municion = municion;
	}

	public void setRangoDisparo(float rangoDisparo) {
		this.rangoDisparo = rangoDisparo;
	}

	public void setTipoDefensa(String tipoDefensa) {
		this.tipoDefensa = tipoDefensa;
	}

}
