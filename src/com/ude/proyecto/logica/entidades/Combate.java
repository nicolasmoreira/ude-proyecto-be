package com.ude.proyecto.logica.entidades;

import java.util.ArrayList;
import java.util.Properties;


public class Combate {

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private String fondoImagen;
	private int idCombate;
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

	public Combate(Properties p) { // combate inicial
		// Properties p = new Properties();
		// InputStream input = null;
		try {
			// input =
			// getClass().getClassLoader().getResourceAsStream("resources/config.properties");
			// p.load(input);

			// System.out.println(p.toString());

			// Combate combate = new Combate(idCombate, nombrePartida, fondoImagen, 600,
			// 800);
			this.nombrePartida = p.getProperty("nombrePartida"); // "Fondo1";
			this.fondoImagen = p.getProperty("fondoImagen"); // "Fondo1";
			this.tamanioAlto = Integer.parseInt(p.getProperty("tamanioAlto"));
			this.tamanioAncho = Integer.parseInt(p.getProperty("tamanioAncho"));

			// creo player 1 y 2
			Jugador player1 = new Jugador(1, p.getProperty("player1"));
			Jugador player2 = new Jugador(2, p.getProperty("player2"));

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

			System.out.println(depCombustible.getUbicacionX() + ' ' + depCombustible.getUbicacionY() );
			System.out.println(torreta.getUbicacionX() + ' ' + torreta.getUbicacionY() );
			// aviones
			ArrayList<Avion> aviones = new ArrayList<Avion>();
			int cantAviones = Integer.parseInt(p.getProperty("cantAviones"));

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion = new Avion(p);

				// lo ubico en la base
				avion.setUbicacionY(depExplosivos.getUbicacionY() + (baseDistancia * i)); // deberia dibujar en las Y
																							// una columna de aviones
				avion.setUbicacionX(depExplosivos.getUbicacionX());
				
				aviones.add(avion);// .insert(avion);
				//System.out.println(avion.toString());
			}

			//System.out.println(aviones.toString());

			//creo artilleria
			ArrayList<Defensa> artillerias = new ArrayList<Defensa>();
			
			
			int cantArtilleria = cantAviones * Integer.parseInt(p.getProperty("multARTILLERIA")); 
			for (int i = 1; i <= cantArtilleria; i++) {
				Defensa artilleria = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				// lo ubico en el campo, es probable que se pisen en la pantalla
				artilleria.setUbicacionY(artilleria.getUbicacionY() + depCombustible.getUbicacionY() + baseDistancia); //para que no caiga en el rango de la base																							
				artilleria.setUbicacionX(artilleria.getUbicacionX() + depCombustible.getUbicacionX() + baseDistancia);//para que no caiga en el rango de la base
				
				artillerias.add(artilleria);// .insert(avion);
				//System.out.println(avion.toString());
			}

			
			
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

			depCombustible2.ubicacionEspejar(this.tamanioAncho, depCombustible.getUbicacionY());
			depExplosivos2.ubicacionEspejar(this.tamanioAncho, depExplosivos.getUbicacionY());
			torreta2.ubicacionEspejar(this.tamanioAncho, torreta.getUbicacionY());

			System.out.println(depCombustible2.getUbicacionX() + ' ' + depCombustible.getUbicacionY() );
			System.out.println(torreta2.getUbicacionX() + ' ' + torreta.getUbicacionY() );
			
			// mantengo los Y del player 1
//			DepCombustible2.setUbicacionY(DepCombustible.getUbicacionY());
//			DepExplosivos2.setUbicacionY(DepExplosivos.getUbicacionY());
//			torreta2.setUbicacionY(torreta.getUbicacionY());

			// aviones
			ArrayList<Avion> aviones2 = new ArrayList<Avion>();

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion2 = new Avion(p);

				// lo ubico en la base
				avion2.setUbicacionY(depExplosivos2.getUbicacionY() + (baseDistancia * i)); // deberia dibujar en las Y
																							// una columna de aviones
				avion2.setUbicacionX(depExplosivos2.getUbicacionX());
				//avion2.ubicacionEspejar(this.tamanioAncho, avion2.getUbicacionY());
				aviones2.add(avion2);// insert(avion2);
			}


			//creo artilleria
			ArrayList<Defensa> artillerias2 = new ArrayList<Defensa>();
			
			
			//int cantArtilleria = cantAviones * Integer.parseInt(p.getProperty("multARTILLERIA")); 
			for (int i = 1; i <= cantArtilleria; i++) {
				//aca adentro espejar cada elemento de la lista anterior
				Defensa artilleria2 = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				// lo ubico en el campo, es probable que se pisen en la pantalla
				artilleria2.setUbicacionY(artilleria2.getUbicacionY() + depCombustible2.getUbicacionY() + baseDistancia); //para que no caiga en el rango de la base																							
				artilleria2.setUbicacionX(artilleria2.getUbicacionX() + depCombustible2.getUbicacionX() + baseDistancia);//para que no caiga en el rango de la base
				
				artillerias2.add(artilleria2);// .insert(avion);
				
			}
			
			//System.out.println(artillerias2.toString());
			
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
