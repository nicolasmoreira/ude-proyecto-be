package com.ude.proyecto.logica.entidades;

public class Defensa extends Componente{
	
	private String tipoDefensa;
	private float rangoDisparo;
	private float demoraDesplazamiento;
	private Proyectil municion;
	
	public Defensa(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, int vida, String sprite,
			String sonido, String tipoDefensa, float rangoDisparo, float demoraDesplazamiento, Proyectil municion) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, vida, sprite, sonido);
		this.tipoDefensa = tipoDefensa;
		this.rangoDisparo = rangoDisparo;
		this.demoraDesplazamiento = demoraDesplazamiento;
		this.municion = municion;
	}

	public String getTipoDefensa() {
		return tipoDefensa;
	}

	public void setTipoDefensa(String tipoDefensa) {
		this.tipoDefensa = tipoDefensa;
	}

	public float getRangoDisparo() {
		return rangoDisparo;
	}

	public void setRangoDisparo(float rangoDisparo) {
		this.rangoDisparo = rangoDisparo;
	}

	public float getDemoraDesplazamiento() {
		return demoraDesplazamiento;
	}

	public void setDemoraDesplazamiento(float demoraDesplazamiento) {
		this.demoraDesplazamiento = demoraDesplazamiento;
	}

	public Proyectil getMunicion() {
		return municion;
	}

	public void setMunicion(Proyectil municion) {
		this.municion = municion;
	}
	
	
}
