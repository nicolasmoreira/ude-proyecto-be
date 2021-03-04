/**
 * 
 */
package com.ude.proyecto.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

		//tomo la partida
		combate = fachada.getPartida();
		JsonObject json1 = new JsonObject();		
		json1.add("partida", new Gson().toJsonTree(combate));
		
		//la muestro como esta ahora
		System.out.println(json1);
		
		//la serializo
		JsonElement jsonElemento = new JsonObject();		
		jsonElemento = new Gson().toJsonTree(fachada.getPartida());
		
		//la deserializo
		combate = new Gson().fromJson(jsonElemento, Combate.class);
		
		//muestro el combate
		System.out.println(combate.getJugadores().get(1));
		System.out.println(combate.getJugadores().get(0).getAvion(3).getUbicacionY());
		
		//la serializo para comparar que sea identica a la que mandamos
		JsonObject json2 = new JsonObject();		
		json2.add("partida", new Gson().toJsonTree(combate));
		System.out.println(json2);
		
		fachada.setCoordenadaComponente(0, 3, Avion.TIPO_AVION, 5, 10, 10);
		System.out.println(combate.getJugadores().get(1));
		System.out.println(combate.getJugadores().get(0).getAvion(3).getUbicacionY()); // get(6).getIdComponente());
																						// //.get(3).getUbicacionX());
	}

}
