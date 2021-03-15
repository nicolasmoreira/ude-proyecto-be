package com.ude.proyecto.logica;

import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.entidades.Avion;
import com.ude.proyecto.logica.entidades.Combate;
import com.ude.proyecto.logica.entidades.Componente;
import com.ude.proyecto.logica.entidades.Defensa;
import com.ude.proyecto.logica.entidades.Provision;
import com.ude.proyecto.persistencia.daos.DAOCombate;

public class Fachada {

	private static Fachada fachada;

	public static Fachada getInstanceFachada() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	private Combate combate;
	private DAOCombate daoCombate;

	private Fachada() {
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

			daoCombate = new DAOCombate(url, user, password);

		} catch (Exception e) {
			System.out.println("Exception creando fachada");
			e.printStackTrace();
		}
	}

	public void cargarPartida(int codigo) throws Exception {
		String combateStr = daoCombate.cargarCombate(codigo);
		this.combate = new Gson().fromJson(combateStr, Combate.class);
	}

	public void finalizarPartida() throws Exception {
		if (this.combate != null) {
			fachada = null;
			this.combate = null;
		}
	}

	public Combate getPartida() {
		return this.combate;
	}

	public Combate iniciarPartida(String player, String color) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		try {
			input = getClass().getClassLoader().getResourceAsStream("config.properties");
			p.load(input);
			this.combate = new Combate(p, player, color);

		} catch (Exception e) {
			System.out.println("Exception creando combate");
			e.printStackTrace();
		}

		return this.combate;
	}

	public ArrayList<HashMap> listarPartidas() {
		return daoCombate.listarCombates();
	}

	public void salvarPartida(Combate combate) throws Exception {
		Instant nowUtc = Instant.now();
		ZoneId americaMontevideo = ZoneId.of("America/Montevideo");
		ZonedDateTime nowAmericaMontevideo = ZonedDateTime.ofInstant(nowUtc, americaMontevideo);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		daoCombate.guardarCombate(nowAmericaMontevideo.format(formatter),
				new Gson().toJsonTree(combate).toString());
	}

	public void setComponente(int idJugador, int idComponente, String TipoComponente, JsonObject data) {
		idJugador = idJugador - 1;

		float ubicacionX = data.get("ubicacionX").getAsFloat();
		float ubicacionY = data.get("ubicacionY").getAsFloat();
		float rotacion = data.get("rotacion").getAsFloat();
		int vida = data.get("vida").getAsInt();

		String sprite = data.get("sprite").getAsString();
		String sonido = data.get("sonido").getAsString();

		if (vida <= 0) {
			vida = 0;
		}
		if (this.combate != null) {
			if (TipoComponente.equals(Avion.TIPO_AVION)) {

				boolean altitudAlta = data.get("altitudAlta").getAsBoolean();
				boolean tieneBomba = data.get("tieneBomba").getAsBoolean();
				float barraCombustible = data.get("barraCombustible").getAsFloat();

				Avion componente = this.combate.getJugadores().get(idJugador).getAvion(idComponente);
				componente.setAltitudAlta(altitudAlta);
				componente.setBarraCombustible(barraCombustible);
				componente.setTieneBomba(tieneBomba);
				componente.setRotacion(rotacion);
				componente.setSonido(sonido);
				componente.setSprite(sprite);
				componente.setTipoComponente(TipoComponente);
				componente.setUbicacionX(ubicacionX);
				componente.setUbicacionY(ubicacionY);
				componente.setVida(vida);

			}
			if (TipoComponente.equals(Componente.TIPO_DEFENSA)) {
				String tipoDefensa = data.get("tipoDefensa").getAsString();

				if (tipoDefensa.equals(Defensa.TIPO_DEFENSA_ARTILLERIA)) {
					Defensa componente = this.combate.getJugadores().get(idJugador).getArtilleria(idComponente);
					componente.setRotacion(rotacion);
					componente.setSonido(sonido);
					componente.setSprite(sprite);
					componente.setUbicacionX(ubicacionX);
					componente.setUbicacionY(ubicacionY);
					componente.setVida(vida);
				}

				if (tipoDefensa.equals(Defensa.TIPO_DEFENSA_TORRETA)) {
					Defensa componente = this.combate.getJugadores().get(idJugador).getTorreta();
					componente.setRotacion(rotacion);
					componente.setSonido(sonido);
					componente.setSprite(sprite);
					componente.setUbicacionX(ubicacionX);
					componente.setUbicacionY(ubicacionY);
					componente.setVida(vida);
				}
			}
			if (TipoComponente.equals(Componente.TIPO_PROVISION)) {
				String tipoProvision = data.get("tipoProvision").getAsString();
				if (tipoProvision.equals(Provision.TIPO_PROVISION_COMBUSTIBLE)) {
					Provision componente = this.combate.getJugadores().get(idJugador).getDepositoCombustible();
					componente.setRotacion(rotacion);
					componente.setSonido(sonido);
					componente.setSprite(sprite);
					componente.setUbicacionX(ubicacionX);
					componente.setUbicacionY(ubicacionY);
					componente.setVida(vida);
				}
				if (tipoProvision.equals(Provision.TIPO_PROVISION_EXPLOSIVOS)) {
					Provision componente = this.combate.getJugadores().get(idJugador).getDepositoExplosivos();
					componente.setRotacion(rotacion);
					componente.setSonido(sonido);
					componente.setSprite(sprite);
					componente.setUbicacionX(ubicacionX);
					componente.setUbicacionY(ubicacionY);
					componente.setVida(vida);
				}
			}
		}
	}

	public void setCoordenadaComponente(int idJugador, int idComponente, String TipoComponente, float x, float y,
			float rotacion) {

		if (this.combate != null && TipoComponente.equals(Avion.TIPO_AVION)) {
			this.combate.getJugadores().get(idJugador).getAvion(idComponente).setUbicacionX(x);
			this.combate.getJugadores().get(idJugador).getAvion(idComponente).setUbicacionY(y);
			this.combate.getJugadores().get(idJugador).getAvion(idComponente).setRotacion(rotacion);
		}

		if (this.combate != null && TipoComponente.equals(Defensa.TIPO_DEFENSA_ARTILLERIA)) {
			this.combate.getJugadores().get(idJugador).getArtilleria(idComponente).setUbicacionX(x);
			this.combate.getJugadores().get(idJugador).getArtilleria(idComponente).setUbicacionY(y);
			this.combate.getJugadores().get(idJugador).getArtilleria(idComponente).setRotacion(rotacion);
		}
	}

	public void setDerribarComponente(int idJugador, int idComponente, String TipoComponente) {
		if (TipoComponente.equals(Avion.TIPO_AVION)) {
			this.combate.getJugadores().get(idJugador).getAvion(idComponente).setVida(0);
		}
		if (TipoComponente.equals(Defensa.TIPO_DEFENSA_ARTILLERIA)) {
			this.combate.getJugadores().get(idJugador).getArtilleria(idComponente).setVida(0);
		}
		if (TipoComponente.equals(Defensa.TIPO_DEFENSA_TORRETA)) {
			this.combate.getJugadores().get(idJugador).getTorreta().setVida(0);
		}
		if (TipoComponente.equals(Provision.TIPO_PROVISION_COMBUSTIBLE)) {
			this.combate.getJugadores().get(idJugador).getDepositoCombustible().setVida(0);
		}
		if (TipoComponente.equals(Provision.TIPO_PROVISION_EXPLOSIVOS)) {
			this.combate.getJugadores().get(idJugador).getDepositoExplosivos().setVida(0);
		}
	}

	public void setPartida(Combate c) {
		Fachada f = getInstanceFachada();
		f.combate = c;
	}

}
