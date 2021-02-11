package com.ude.proyecto.persistencia.consultas;

public class Consultas {

	public Consultas() {
		// TODO Auto-generated constructor stub
	}

	/* consultas de Avion */

	public String darAvion() {
		// devuelve un Avion
		String consulta = "SELECT * FROM juego.avion where codigo = ?";
		return consulta;
	}

}
