package com.ude.proyecto.logica.entidades;

public class Proyectil extends Componente{

	private String tipoProyectil;
	private int cantidad;

	public Proyectil(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, int vida,
			String sprite, String sonido, String tipoProyectil, int cantidad) {
		super(idComponente, tipoComponente, ubicacionX, ubicacionY, vida, sprite, sonido);
		this.tipoProyectil = tipoProyectil;
		this.cantidad = cantidad;
	}

	public String getTipoProyectil() {
		return tipoProyectil;
	}

	public void setTipoProyectil(String tipoProyectil) {
		this.tipoProyectil = tipoProyectil;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
