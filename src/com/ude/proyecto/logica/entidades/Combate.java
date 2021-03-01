package com.ude.proyecto.logica.entidades;

import java.util.ArrayList;
import java.util.Properties;

public class Combate {

	private String fondoImagen;
	private int idCombate;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private String nombrePartida;
	private int tamanioAlto;
	private int tamanioAncho;

	public Combate(int idCombate, String nombrePartida, String fondoImagen, int tamanioAlto, int tamanioAncho) {
		super();
		this.idCombate = idCombate;
		this.nombrePartida = nombrePartida;
		this.fondoImagen = fondoImagen;
		this.tamanioAlto = tamanioAlto;
		this.tamanioAncho = tamanioAncho;
	}

	public Combate(Properties p, String player, String color) { // combate inicial
		// Properties p = new Properties();
		// InputStream input = null;
		try {

			this.nombrePartida = p.getProperty("nombrePartida"); // "Fondo1";
			this.fondoImagen = p.getProperty("fondoImagen"); // "Fondo1";
			this.tamanioAlto = Integer.parseInt(p.getProperty("tamanioAlto"));
			this.tamanioAncho = Integer.parseInt(p.getProperty("tamanioAncho"));

			System.out.println(player);
			System.out.println(p.getProperty("player1"));

			Jugador player1 = new Jugador(1, p.getProperty("player1"), "");
			Jugador player2 = new Jugador(2, p.getProperty("player2"), "");

			String elOtroBando = null;

			if (color.equals(Jugador.ROJO))
				elOtroBando = Jugador.AZUL;
			else
				elOtroBando = Jugador.ROJO;

			// creo player 1 y 2
			if (player.equals(p.getProperty("player1"))) {
				player1.setBando(color);
				player2.setBando(elOtroBando);
			} else {
				player1.setBando(elOtroBando);
				player2.setBando(color);
			}

			/*--- componentes player 1*/
			// creo combustible
			Provision depCombustible = new Provision(Provision.TIPO_PROVISION_COMBUSTIBLE, p);
			Provision depExplosivos = new Provision(Provision.TIPO_PROVISION_EXPLOSIVOS, p);
			Defensa torreta = new Defensa(Defensa.TIPO_DEFENSA_TORRETA, p);

			// creo la base virtual, una L con eje como combustible

			int baseDistancia = Integer.parseInt(p.getProperty("baseDistancia"));

			// tomo el X DepCombustible y lo alineo con los explosivos
			depExplosivos.setUbicacionX(depCombustible.getUbicacionX());
			depExplosivos.setUbicacionY(depCombustible.getUbicacionY() + baseDistancia);
			torreta.setUbicacionX(depCombustible.getUbicacionX() + baseDistancia);
			torreta.setUbicacionY(depCombustible.getUbicacionY());

			System.out.println("-----------Primera vez-------------");

			System.out.println(depCombustible.getUbicacionX());
			System.out.println(depCombustible.getUbicacionY());
			// System.out.println(torreta.getUbicacionX());
			// aviones
			ArrayList<Avion> aviones = new ArrayList<Avion>();
			int cantAviones = Integer.parseInt(p.getProperty("cantAviones"));

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion = new Avion(p);

				// lo ubico en la base
				avion.setUbicacionY(depExplosivos.getUbicacionY() + (baseDistancia * i)); // deberia dibujar en las Y
																							// una columna de aviones
				avion.setUbicacionX(depExplosivos.getUbicacionX());

				avion.setSprite(avion.getSprite() + player1.getBando());

				aviones.add(avion);// .insert(avion);
				// System.out.println(avion.toString());
			}

			// System.out.println(aviones.toString());

			int limiteCampo = Integer.parseInt(p.getProperty("limiteCampo"));

			// creo artilleria
			ArrayList<Defensa> artillerias = new ArrayList<Defensa>();

			int cantArtilleria = cantAviones * Integer.parseInt(p.getProperty("multARTILLERIA"));
			for (int i = 1; i <= cantArtilleria; i++) {
				Defensa artilleria = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				if (i <= cantArtilleria / 2) {// si es la 1ra mitad del camino, dibujo diagonal arriba
					artilleria.setUbicacionX(
							artilleria.ubicacionInicial(limiteCampo - depCombustible.getUbicacionX() - baseDistancia,
									depCombustible.getUbicacionX() + baseDistancia));
					artilleria
							.setUbicacionY(artilleria.ubicacionInicial(depCombustible.getUbicacionY(), baseDistancia));

				} else { // si es la 2da mitad del camino, dibujo diagonal abajo
					artilleria.setUbicacionX(
							artilleria.ubicacionInicial(limiteCampo - depCombustible.getUbicacionX() - baseDistancia,
									depCombustible.getUbicacionX() + baseDistancia));
					artilleria.setUbicacionY(
							artilleria.ubicacionInicial(limiteCampo - depCombustible.getUbicacionY() - baseDistancia,
									depCombustible.getUbicacionY() + baseDistancia));

				}

				artillerias.add(artilleria);// .insert(avion);
				System.out.println("-----------");
				System.out.println(artilleria.getUbicacionX());
				System.out.println(artilleria.getUbicacionY());
			}

			// System.out.println(artillerias. toString());

			// asigno a player 1
			player1.setDepositoCombustible(depCombustible);
			player1.setDepositoExplosivos(depExplosivos);
			player1.setTorreta(torreta);
			player1.setAviones(aviones);
			player1.setArtillerias(artillerias);

			/*----------------------Espejo player 2 ---------------------*/

			// creo combustible
			Provision depCombustible2 = new Provision(Provision.TIPO_PROVISION_COMBUSTIBLE, p);
			Provision depExplosivos2 = new Provision(Provision.TIPO_PROVISION_EXPLOSIVOS, p);
			Defensa torreta2 = new Defensa(Defensa.TIPO_DEFENSA_TORRETA, p);

			// espejo para el player 2, mantengo los Y del player 1

			depCombustible2.setUbicacionX(depCombustible.getUbicacionX());
			depCombustible2.ubicacionEspejar(this.tamanioAncho, depCombustible.getUbicacionY());
			// System.out.println(depCombustible2.getUbicacionX());

			depExplosivos2.setUbicacionX(depExplosivos.getUbicacionX());
			depExplosivos2.ubicacionEspejar(this.tamanioAncho, depExplosivos.getUbicacionY());

			torreta2.setUbicacionX(torreta.getUbicacionX());
			torreta2.ubicacionEspejar(this.tamanioAncho, torreta.getUbicacionY());

			// System.out.println(depCombustible2.getUbicacionX() + ' ' +
			// depCombustible.getUbicacionY() );
			// System.out.println(torreta2.getUbicacionX());

			// aviones
			ArrayList<Avion> aviones2 = new ArrayList<Avion>();

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion2 = new Avion(p);

				// lo ubico en la base
				avion2.setUbicacionY(depExplosivos2.getUbicacionY() + (baseDistancia * i)); // deberia dibujar en las Y
																							// una columna de aviones
				avion2.setUbicacionX(depExplosivos2.getUbicacionX());
				avion2.setSprite(avion2.getSprite() + player2.getBando());

				aviones2.add(avion2);// insert(avion2);
			}

			// creo artilleria
			ArrayList<Defensa> artillerias2 = new ArrayList<Defensa>();

			// int cantArtilleria = cantAviones *
			// Integer.parseInt(p.getProperty("multARTILLERIA"));
			for (int i = 1; i <= cantArtilleria; i++) {
				// aca adentro espejar cada elemento de la lista anterior
				Defensa artilleria2 = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				Defensa atilleriaAux = artillerias.get(i - 1); // sopongo que va del 0 en adelante
				artilleria2.setUbicacionX(atilleriaAux.getUbicacionX());
				artilleria2.ubicacionEspejar(this.tamanioAncho, atilleriaAux.getUbicacionY());

//				System.out.println("-----------");
//				System.out.println(artilleria2.getUbicacionX());
//				System.out.println(artilleria2.getUbicacionY());

				artillerias2.add(artilleria2);// .insert(avion);

			}

			// asigno a player2
			player2.setDepositoCombustible(depCombustible2);
			player2.setDepositoExplosivos(depExplosivos2);
			player2.setTorreta(torreta2);
			player2.setAviones(aviones2);
			player2.setArtillerias(artillerias2);

			// creo jugadores
			this.jugadores.clear();
			this.jugadores.add(player1);
			this.jugadores.add(player2);

		} catch (Exception e) {
			System.out.println("Exception creando combate");
			e.printStackTrace();
		}
	}

	public String getFondoImagen() {
		return fondoImagen;
	}

	public int getIdCombate() {
		return idCombate;
	}

	public ArrayList<Jugador> getJugadores() {
		return this.jugadores;
	}

	public String getNombrePartida() {
		return nombrePartida;
	}

	public int getTamanioAlto() {
		return tamanioAlto;
	}

	public int getTamanioAncho() {
		return tamanioAncho;
	}

	public void setFondoImagen(String fondoImagen) {
		this.fondoImagen = fondoImagen;
	}

	public void setIdCombate(int idCombate) {
		this.idCombate = idCombate;
	}

	public void setJugadores(ArrayList<Jugador> j) {
		this.jugadores = j;
	}

	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

	public void setTamanioAlto(int tamanioAlto) {
		this.tamanioAlto = tamanioAlto;
	}

	public void setTamanioAncho(int tamanioAncho) {
		this.tamanioAncho = tamanioAncho;
	}

}
