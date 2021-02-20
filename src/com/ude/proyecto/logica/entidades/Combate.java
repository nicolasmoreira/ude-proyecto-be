package com.ude.proyecto.logica.entidades;

import java.io.InputStream;
import java.util.Properties;

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

			//Combate combate = new Combate(idCombate, nombrePartida, fondoImagen, 600, 800);
			this.nombrePartida =  p.getProperty("nombrePartida"); // "Fondo1";
			this.fondoImagen   =  p.getProperty("fondoImagen"); // "Fondo1";
			this.tamanioAlto   =  Integer.parseInt(p.getProperty("tamanioAlto"));
			this.tamanioAncho  =  Integer.parseInt(p.getProperty("tamanioAncho"));
						
			//creo combustible		
			Provision DepCombustible = new Provision("Combustible", p);
			Provision DepExplosivos  = new Provision("Explosivos", p);

			//creo  player 1 y 2
			Jugador player1 = new Jugador(1, p.getProperty("player1"));
			Jugador player2 = new Jugador(2, p.getProperty("player1"));
			
			player1.setDepositoCombustible(DepCombustible);
			player1.setDepositoExplosivos(DepExplosivos);
			
			player2.setDepositoCombustible(DepCombustible);
			player2.setDepositoExplosivos(DepExplosivos);
			
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
