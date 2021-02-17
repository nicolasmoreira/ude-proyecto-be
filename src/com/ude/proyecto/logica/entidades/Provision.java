package com.ude.proyecto.logica.entidades;

public class Provision extends Componente{
	
	private String tipoProvision;
	private float cantProvision;

	public Provision(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, int vida,
			String sprite, String sonido, String tipoProvision, float cantProvision) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, vida, sprite, sonido);
		this.tipoProvision = tipoProvision;
		this.cantProvision = cantProvision;
	}

	public String getTipoProvision() {
		return tipoProvision;
	}

	public void setTipoProvision(String tipoProvision) {
		this.tipoProvision = tipoProvision;
	}

	public float getCantProvision() {
		return cantProvision;
	}

	public void setCantProvision(float cantProvision) {
		this.cantProvision = cantProvision;
	}
}
