package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Provision extends Componente {

	private float cantProvision;
	private String tipoProvision;
	public static final String TIPO_PROVISION_COMBUSTIBLE = "COMBUSTIBLE";
	public static final String TIPO_PROVISION_EXPLOSIVOS = "EXPLOSIVOS";

	public Provision() {
		super();
	}

	public Provision(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido, String tipoProvision, float cantProvision) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.tipoProvision = tipoProvision;
		this.cantProvision = cantProvision;
	}

	/* aca creo los por defecto */
	public Provision(String tipoProvision, Properties p) {// provision
		super("Provision", p);

		this.tipoProvision = tipoProvision;

		// combustible o explosivos
		if (tipoProvision.equals(Provision.TIPO_PROVISION_COMBUSTIBLE) ) {
			this.cantProvision = Float.parseFloat(p.getProperty("cantProvisionCombustible"));
			this.sprite = p.getProperty("SpDepositoCombustible");
			this.sonido = p.getProperty("SdDepositoCombustible");
		} else {
			this.cantProvision = Float.parseFloat(p.getProperty("cantProvisionExplosivos"));
			this.sprite = p.getProperty("SpDepositoExplosivos");
			this.sonido = p.getProperty("SdDepositoExplosivos");
		}

		/*
		 * aca crear un iniciador por defecto de combustible (lo brinda el componente)
		 */
		/*
		 * this.setIdComponente(1); //this.setTipoProvision("COMBUSTIBLE");
		 * this.setRotacion(0); this.setVida(1);
		 * 
		 */
		// this.setTipoComponente(tipComp);
	}

	public float getCantProvision() {
		return cantProvision;
	}

	public String getTipoProvision() {
		return tipoProvision;
	}

	public void setCantProvision(float cantProvision) {
		this.cantProvision = cantProvision;
	}

	public void setTipoProvision(String tipoProvision) {
		this.tipoProvision = tipoProvision;
	}

}
