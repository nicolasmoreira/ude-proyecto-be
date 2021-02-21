package com.ude.proyecto.logica.entidades;

public class Proyectil extends Componente {

	private int cantidad;
	private String tipoProyectil;

	public Proyectil(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido, String tipoProyectil, int cantidad) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, rotacion, vida, sprite, sonido);
		this.tipoProyectil = tipoProyectil;
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getTipoProyectil() {
		return tipoProyectil;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setTipoProyectil(String tipoProyectil) {
		this.tipoProyectil = tipoProyectil;
	}

}
