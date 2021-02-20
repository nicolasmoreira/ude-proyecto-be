package com.ude.proyecto.logica;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.entidades.Combate;
import com.ude.proyecto.persistencia.daos.DAOCombate;



public class Fachada {

	private static Fachada fachada;
	private Combate combate;

	public static Fachada getInstanceFachada() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	private Fachada() {
		// combate = new Combate();
		Properties p = new Properties();
		InputStream input = null;
		try {
			/*
			 * input =
			 * getClass().getClassLoader().getResourceAsStream("resources/config.properties"
			 * ); p.load(input);
			 * 
			 * String driver = p.getProperty("db_driver"); String host =
			 * p.getProperty("db_server"); String port = p.getProperty("db_port"); String
			 * database = p.getProperty("db_database");
			 * 
			 * String url = "jdbc:mysql://" + host + ":" + port + "/" + database; String
			 * user = p.getProperty("db_user"); String password =
			 * p.getProperty("db_password");
			 * 
			 * Class.forName(driver); daoPartidas = new DAOPartidas(url, user, password);
			 */
		} catch (Exception e) {
			System.out.println("Exception creando fachada");
			e.printStackTrace();
		}
	}

	public Gson iniciarPartida() throws Exception {
		//JsonObject json = new JsonObject();
		Gson gson = new Gson();
		Properties p = new Properties();
		InputStream input = null;
		try {
			input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");			
			p.load(input);
	
			combate = new Combate(p);//le paso la properties para crear la primer partida						
			
			gson.toJson(combate);
			
		} catch (Exception e) {
			System.out.println("Exception creando combate");
			e.printStackTrace();
		}			
		System.out.println(gson);
		return gson ;
	}

	public JsonObject unirsePartida() throws Exception {
		return null;
	}

	public void salvarPartida(Combate c) throws Exception {
	}

	public JsonObject cargarPartida(int i) throws Exception {
		return null;
	}

}
