/**
 * 
 */
package com.ude.proyecto.test;

import com.ude.proyecto.logica.Fachada;
import com.ude.proyecto.logica.entidades.Avion;
import com.ude.proyecto.logica.entidades.Combate;
import com.ude.proyecto.logica.entidades.Jugador;

/**
 * @author MTory
 *
 */
public class MainTesting {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Fachada fachada = Fachada.getInstanceFachada();

		Combate combate = null;
		String player = "Player1"; // a fuego por ahora
		String bando = Jugador.ROJO; // a fuego por ahora
		combate = fachada.iniciarPartida(player, bando);

		fachada.setCoordenadaComponente(0, 3, Avion.TIPO_AVION, 5, 10, 10);
		System.out.println(combate.getJugadores().get(1));
		System.out.println(combate.getJugadores().get(0).getAvion(3).getUbicacionY()); // get(6).getIdComponente());
																						// //.get(3).getUbicacionX());
	}

}
