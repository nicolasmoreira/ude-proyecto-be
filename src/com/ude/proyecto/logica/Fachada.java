package com.ude.proyecto.logica;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.ude.proyecto.logica.colecciones.Aviones;
import com.ude.proyecto.logica.colecciones.Jugadores;
import com.ude.proyecto.logica.entidades.Avion;
import com.ude.proyecto.logica.entidades.Jugador;
import com.ude.proyecto.logica.entidades.Partida;
import com.ude.proyecto.persistencia.daos.DAOJugadores;
import com.ude.proyecto.persistencia.daos.DAOPartidas;

public class Fachada {

	private static Fachada fachada;

	public static Fachada getInstanceFachada() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	private DAOJugadores daoJugadores;
	private DAOPartidas daoPartidas;

	private Partida partida;

	private Fachada() {
		partida = new Partida();
		Properties p = new Properties();
		InputStream input = null;

		try {
			/*input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");
			p.load(input);

			String driver = p.getProperty("db_driver");
			String host = p.getProperty("db_server");
			String port = p.getProperty("db_port");
			String database = p.getProperty("db_database");

			String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
			String user = p.getProperty("db_user");
			String password = p.getProperty("db_password");

			Class.forName(driver);

			daoJugadores = new DAOJugadores(url, user, password);
			daoPartidas = new DAOPartidas(url, user, password);*/
		} catch (Exception e) {
			System.out.println("Exception creando fachada");
			e.printStackTrace();
		}

	}

	public JsonObject crearPartida(String player, int tamanioEscenarioX, int tamanioEscenarioY) throws Exception {

		JsonObject json = new JsonObject();

		Avion v1 = null, v2 = null;

		if (player.equals("player1")) {

			v1 = new Avion(1, 1, 1);
			v2 = new Avion(2, 2, 2);

		} else if (player.equals("player2")) {

			v1 = new Avion(4, 4, 4);
			v2 = new Avion(5, 5, 5);

		}

		Aviones aviones = new Aviones();
		aviones.put(v1);
		aviones.put(v2);

		Jugador jugador = new Jugador(1, player, aviones);

		Jugadores jugadores = partida.getJugadores();
		jugadores.put(jugador);

		partida.setJugadores(jugadores);
		partida.setTamanioEscenarioX(tamanioEscenarioX);
		partida.setTamanioEscenarioY(tamanioEscenarioY);

		json.addProperty("mensaje", "OK");

		return json;

	}

}
