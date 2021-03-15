package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public class Provision extends Componente {

	public static final String TIPO_PROVISION_COMBUSTIBLE = "COMBUSTIBLE";
	public static final String TIPO_PROVISION_EXPLOSIVOS = "EXPLOSIVOS";

	private float cantProvision;
	private String tipoProvision;

	public Provision() {
		super();
	}

	public Provision(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido, String tipoProvision, float cantProvision) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.tipoProvision = tipoProvision;
		this.cantProvision = cantProvision;
	}

	public Provision(String tipoProvision, Properties p) {
		super(TIPO_PROVISION, p);

		this.tipoProvision = tipoProvision;

		if (tipoProvision.equals(Provision.TIPO_PROVISION_COMBUSTIBLE)) {
			this.cantProvision = Float.parseFloat(p.getProperty("cantProvisionCombustible"));
			this.sprite = p.getProperty("SpDepositoCombustible");
			this.sonido = p.getProperty("SdDepositoCombustible");
		} else {
			this.cantProvision = Float.parseFloat(p.getProperty("cantProvisionExplosivos"));
			this.sprite = p.getProperty("SpDepositoExplosivos");
			this.sonido = p.getProperty("SdDepositoExplosivos");
		}
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
