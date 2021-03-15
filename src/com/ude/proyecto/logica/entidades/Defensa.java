package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Defensa extends Componente {

	public static final String TIPO_DEFENSA_ARTILLERIA = "ARTILLERIA";
	public static final String TIPO_DEFENSA_TORRETA = "TORRETA";
	private float demoraDesplazamiento;
	private float rangoDisparo;
	private String tipoDefensa;

	public Defensa() {
		super();
	}

	public Defensa(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido, String tipoDefensa, float rangoDisparo, float demoraDesplazamiento) {

		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.tipoDefensa = tipoDefensa;
		this.rangoDisparo = rangoDisparo;
		this.demoraDesplazamiento = demoraDesplazamiento;
	}

	public Defensa(String tipoDefensa, Properties p) {
		super(TIPO_DEFENSA, p);
		this.tipoDefensa = tipoDefensa;
		this.rangoDisparo = Float.parseFloat(p.getProperty("rangDisp" + tipoDefensa.trim()));
		this.demoraDesplazamiento = Float.parseFloat(p.getProperty("demDesp" + tipoDefensa.trim()));
		this.sprite = p.getProperty("Sp" + tipoDefensa.trim());
		this.sonido = p.getProperty("Sd" + tipoDefensa.trim());
	}

	public float getDemoraDesplazamiento() {
		return demoraDesplazamiento;
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

	public void setRangoDisparo(float rangoDisparo) {
		this.rangoDisparo = rangoDisparo;
	}

	public void setTipoDefensa(String tipoDefensa) {
		this.tipoDefensa = tipoDefensa;
	}

}
