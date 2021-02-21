package com.ude.proyecto.logica.entidades;

import java.io.InputStream;
import java.util.Properties;

import com.ude.proyecto.logica.colecciones.Aviones;
import com.ude.proyecto.logica.colecciones.Jugadores;

public class Combate {

	private int idCombate;
	private String nombrePartida;
	private Jugadores combateJugadores;
	private String fondoImagen;
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

	
	public Combate(Properties p) { //combate inicial
		//Properties p = new Properties();
		//InputStream input = null;
		try {
			//input = getClass().getClassLoader().getResourceAsStream("resources/config.properties");			
			//p.load(input);
			
			//System.out.println(p.toString());
			
			//Combate combate = new Combate(idCombate, nombrePartida, fondoImagen, 600, 800);
			this.nombrePartida =  p.getProperty("nombrePartida"); // "Fondo1";
			this.fondoImagen   =  p.getProperty("fondoImagen"); // "Fondo1";
			this.tamanioAlto   =  Integer.parseInt(p.getProperty("tamanioAlto"));
			this.tamanioAncho  =  Integer.parseInt(p.getProperty("tamanioAncho"));
			
			
			//creo  player 1 y 2
			Jugador player1 = new Jugador(1, p.getProperty("player1"));			
			Jugador player2 = new Jugador(2, p.getProperty("player2"));
			
			/*--- componentes player 1*/
			//creo combustible		
			Provision DepCombustible = new Provision("Combustible", p);
			Provision DepExplosivos  = new Provision("Explosivos", p);
			Defensa torreta  = new Defensa("Torreta", p);
			
			
			//creo la base virtual, una L con eje como combustible
			
			int baseDistancia = Integer.parseInt(p.getProperty("baseDistancia"));
			
			// tomo el X DepCombustible y lo alineo con los explosivos			
			DepExplosivos.setUbicacionX(DepCombustible.getUbicacionX());			
			DepExplosivos.setUbicacionY(DepCombustible.getUbicacionY() + baseDistancia );
			torreta.setUbicacionX(DepCombustible.getUbicacionX() + baseDistancia);
			torreta.setUbicacionY(DepCombustible.getUbicacionY());
			
			//aviones			
			Aviones aviones = new Aviones();
			int cantAviones = Integer.parseInt(p.getProperty("cantAviones"));
			
			Avion avion = null;
			for (int i=1; i <= cantAviones; ++i ) {
				avion = new Avion(p);
				
				//lo ubico en la base
				avion.setUbicacionY(DepExplosivos.getUbicacionY() + (baseDistancia * i)); //deberia dibujar en las Y una columna de aviones				
				aviones.insert(avion);
				System.out.println(avion.toString());
			}			
			
			System.out.println(aviones.toString());
			
			
			
			//asigno a player 1
			player1.setDepositoCombustible(DepCombustible);
			player1.setDepositoExplosivos(DepExplosivos);
			player1.setTorreta(torreta);
			player1.setJaviones(aviones);
			
			/*----------------------Espejo player 2 ---------------------*/
			
			
			//creo combustible		
			Provision DepCombustible2 = new Provision("Combustible", p);
			Provision DepExplosivos2  = new Provision("Explosivos", p);
			Defensa torreta2  = new Defensa("Torreta", p);
			
			
			//espejo para el player 2, mantengo los Y del player 1
			
			DepCombustible2.UbicacionEspejar(this.tamanioAncho, DepCombustible.getUbicacionY());
			DepExplosivos2.UbicacionEspejar(this.tamanioAncho, DepExplosivos.getUbicacionY());
			torreta2.UbicacionEspejar(this.tamanioAncho, torreta.getUbicacionY());
			
			//mantengo los Y del player 1
//			DepCombustible2.setUbicacionY(DepCombustible.getUbicacionY());
//			DepExplosivos2.setUbicacionY(DepExplosivos.getUbicacionY());
//			torreta2.setUbicacionY(torreta.getUbicacionY());

			
			//aviones			
			Aviones aviones2 = new Aviones();
			
			Avion avion2 = null;
			for (int i=1; i <= cantAviones; ++i ) {
				avion2 = new Avion(p);
				
				//lo ubico en la base
				avion2.setUbicacionY(DepExplosivos2.getUbicacionY() + (baseDistancia * i)); //deberia dibujar en las Y una columna de aviones				
				avion2.UbicacionEspejar(this.tamanioAncho, avion2.getUbicacionY());
				aviones2.insert(avion2);
			}
			
			//asigno a player2
			player2.setDepositoCombustible(DepCombustible2);
			player2.setDepositoExplosivos(DepExplosivos2);
			player2.setTorreta(torreta2);
			player2.setJaviones(aviones2);
			
			//creo jugadores
			Jugadores jugadores = new Jugadores();
			jugadores.insert(player1);
			jugadores.insert(player2);		
			
			this.combateJugadores = jugadores;
	
		} catch (Exception e) {
			System.out.println("Exception creando combate");
			e.printStackTrace();
		}
	}
	
	public int getIdCombate() {
		return idCombate;
	}

	public void setIdCombate(int idCombate) {
		this.idCombate = idCombate;
	}

	public String getNombrePartida() {
		return nombrePartida;
	}

	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}

	public Jugadores getCombateJugadores() {
		return combateJugadores;
	}

	public void setCombateJugadores(Jugadores combateJugadores) {
		this.combateJugadores = combateJugadores;
	}

	public String getFondoImagen() {
		return fondoImagen;
	}

	public void setFondoImagen(String fondoImagen) {
		this.fondoImagen = fondoImagen;
	}

	public int getTamanioAlto() {
		return tamanioAlto;
	}

	public void setTamanioAlto(int tamanioAlto) {
		this.tamanioAlto = tamanioAlto;
	}

	public int getTamanioAncho() {
		return tamanioAncho;
	}

	public void setTamanioAncho(int tamanioAncho) {
		this.tamanioAncho = tamanioAncho;
	}

	
}
