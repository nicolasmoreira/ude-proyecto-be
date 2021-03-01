package com.ude.proyecto.logica;

import java.io.InputStream;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.entidades.Avion;
import com.ude.proyecto.logica.entidades.Combate;
import com.ude.proyecto.logica.entidades.Defensa;
import com.ude.proyecto.logica.entidades.Provision;

public class Fachada {

	private static Fachada fachada;

	public static Fachada getInstanceFachada() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	private Combate combate;

	/**
	 * 
	 */
	private Fachada() {
		// combate = new Combate();
		Properties p = new Properties();
		InputStream input = null;
		try {
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			p.load(input);
			String driver = p.getProperty("db_driver");
			String host = p.getProperty("db_server");
			String port = p.getProperty("db_port");
			String database = p.getProperty("db_database");

			String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?serverTimezone=UTC";
			String user = p.getProperty("db_user");
			String password = p.getProperty("db_password");
			Class.forName(driver);

		} catch (Exception e) {
			System.out.println("Exception creando fachada");
			e.printStackTrace();
		}
	}

	public JsonObject cargarPartida(int i) throws Exception {
		return null;
	}

	public Combate iniciarPartida(String player, String bando) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
//		System.out.println(bando);
		try {
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			p.load(input);
			this.combate = new Combate(p, player, bando);

		} catch (Exception e) {
			System.out.println("Exception creando combate");
			e.printStackTrace();
		}

		return this.combate;
	}
	
	public Combate getPartida() {
		return this.combate;
	}

	public void salvarPartida(Combate c) throws Exception {
	}

	public JsonObject unirsePartida() throws Exception {
		return null;
	}
	
	public void setCoordenadaComponente(int idJugador, int idComponente, String TipoComponente,  float x, float y, float rotacion) {
				
		if (TipoComponente == Avion.TIPO_AVION) { 
			  
//			this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).setUbicacionX(x);
//			this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).setUbicacionY(y);
//			this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).setRotacion(rotacion);
			
//			System.out.println(this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).getUbicacionX());
			System.out.println(this.combate.toString());
			System.out.println(combate.getJugadores().get(idJugador).getAviones());
		}
		if (TipoComponente == Defensa.TIPO_DEFENSA_ARTILLERIA) { 
//			this.combate.getJugadores().get(idJugador).getArtillerias().get(idComponente).setUbicacionX(x);
//			this.combate.getJugadores().get(idJugador).getArtillerias().get(idComponente).setUbicacionY(y);
//			this.combate.getJugadores().get(idJugador).getArtillerias().get(idComponente).setRotacion(rotacion);
		}		
	}
	
	public void setDerribarComponente(int idJugador, int idComponente, String TipoComponente) {
		if (TipoComponente == Avion.TIPO_AVION) { 
			this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).setVida(0);
			//System.out.println(this.combate.getJugadores().get(idJugador).getAviones().get(idComponente).getUbicacionX());
		}
		if (TipoComponente == Defensa.TIPO_DEFENSA_ARTILLERIA) { 
			this.combate.getJugadores().get(idJugador).getArtillerias().get(idComponente).setVida(0);			
		}
		if (TipoComponente == Defensa.TIPO_DEFENSA_TORRETA) { 
			this.combate.getJugadores().get(idJugador).getTorreta().setVida(0);			
		}
		if (TipoComponente == Provision.TIPO_PROVISION_COMBUSTIBLE) { 
			this.combate.getJugadores().get(idJugador).getDepositoCombustible().setVida(0);			
		}
		if (TipoComponente == Provision.TIPO_PROVISION_EXPLOSIVOS) { 
			this.combate.getJugadores().get(idJugador).getDepositoExplosivos().setVida(0);			
		}
	}

}
