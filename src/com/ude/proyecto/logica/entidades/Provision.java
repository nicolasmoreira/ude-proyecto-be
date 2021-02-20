package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Provision extends Componente {

	private String tipoProvision;
	private float cantProvision;

	public Provision(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY,float rotacion, int vida,
			String sprite, String sonido, String tipoProvision, float cantProvision) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY,rotacion, vida, sprite, sonido);
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
	
	/*aca creo los por defecto*/	
	public Provision(String tipProv, Properties p) {//provision 
		super("Provision", p);
				
		this.tipoProvision = tipProv;
				
		//combustible o explosivos
		if (tipProv == "Combustible") {			
			this.cantProvision = Integer.parseInt(p.getProperty("cantProvisionCombustible"));
			this.setSprite(p.getProperty("SpDepositoCombustible"));
			this.setSonido(p.getProperty("SdDepositoCombustible"));
		}		
		else {			
			this.cantProvision = Integer.parseInt(p.getProperty("cantProvisionExplosivos"));
			this.setSprite(p.getProperty("SpDepositoExplosivos"));
			this.setSonido(p.getProperty("SdDepositoExplosivos"));
		}
				
		
		this.setUbicacionX(100);
		this.setUbicacionY(100);
			
		/*aca crear un iniciador por defecto de combustible (lo brinda el componente)*/
		/*
		this.setIdComponente(1);
		//this.setTipoProvision("COMBUSTIBLE");
		this.setRotacion(0);
		this.setVida(1);		
		
		*/
		//this.setTipoComponente(tipComp);
	}	
	
}
