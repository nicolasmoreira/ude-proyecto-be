package com.ude.proyecto.logica;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.JsonObject;
import com.ude.proyecto.logica.colecciones.Aviones;
import com.ude.proyecto.logica.colecciones.JugadoresOLD;
import com.ude.proyecto.logica.entidades.AvionOLD;
import com.ude.proyecto.logica.entidades.JugadorOLD;
import com.ude.proyecto.logica.entidades.PartidaOLD;

public class FachadaOLD {

	private static FachadaOLD fachada;

	public static FachadaOLD getInstanceFachada() {
		if (fachada == null) {
			fachada = new FachadaOLD();
		}
		return fachada;
	}

	//private DAOJugadores daoJugadores;
	//private DAOPartidas daoPartidas;

	private PartidaOLD partida;

	private FachadaOLD() {
		partida = new PartidaOLD();
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

		AvionOLD v1 = null, v2 = null;

		if (player.equals("player1")) {

			v1 = new AvionOLD(1, 1, 1);
			v2 = new AvionOLD(2, 2, 2);

		} else if (player.equals("player2")) {

			v1 = new AvionOLD(4, 4, 4);
			v2 = new AvionOLD(5, 5, 5);

		}

		Aviones aviones = new Aviones();
		//aviones.put(v1);
		//aviones.put(v2);

		JugadorOLD jugador = new JugadorOLD(1, player, aviones);

		JugadoresOLD jugadores = partida.getJugadores();
		jugadores.put(jugador);

		partida.setJugadores(jugadores);
		partida.setTamanioEscenarioX(tamanioEscenarioX);
		partida.setTamanioEscenarioY(tamanioEscenarioY);

		json.addProperty("mensaje", "OK");

		return json;

	}

}
