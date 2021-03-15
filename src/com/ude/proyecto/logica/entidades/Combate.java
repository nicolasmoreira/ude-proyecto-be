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

	public Combate(Properties p, String player, String color) {
		try {

			this.nombrePartida = p.getProperty("nombrePartida"); // "Fondo1";
			this.fondoImagen = p.getProperty("fondoImagen"); // "Fondo1";
			this.tamanioAlto = Integer.parseInt(p.getProperty("tamanioAlto"));
			this.tamanioAncho = Integer.parseInt(p.getProperty("tamanioAncho"));

			Jugador player1 = new Jugador(1, p.getProperty("player1"), "");
			Jugador player2 = new Jugador(2, p.getProperty("player2"), "");
			String elOtroBando = null;

			if (color.equals(Jugador.ROJO)) {
				elOtroBando = Jugador.AZUL;
			}else {
				elOtroBando = Jugador.ROJO;
			}

			if (player.equals(p.getProperty("player1"))) {
				player1.setBando(color);
				player2.setBando(elOtroBando);
			} else {
				player1.setBando(elOtroBando);
				player2.setBando(color);
			}

			Provision depCombustible = new Provision(Provision.TIPO_PROVISION_COMBUSTIBLE, p);
			Provision depExplosivos = new Provision(Provision.TIPO_PROVISION_EXPLOSIVOS, p);
			Defensa torreta = new Defensa(Defensa.TIPO_DEFENSA_TORRETA, p);

			int baseDistancia = Integer.parseInt(p.getProperty("baseDistancia"));
			int verticeX = Integer.parseInt(p.getProperty("verticeX"));// 1;
			
			depCombustible.setUbicacionX(verticeX);
			
			depExplosivos.setUbicacionX(depCombustible.getUbicacionX() + baseDistancia / 2);
			depExplosivos.setUbicacionY(depCombustible.getUbicacionY() + baseDistancia);
			depExplosivos.setSprite("IZQ" + depExplosivos.getSprite());

			torreta.setUbicacionX(depCombustible.getUbicacionX() + baseDistancia);
			torreta.setUbicacionY(depCombustible.getUbicacionY());

			ArrayList<Avion> aviones = new ArrayList<Avion>();
			int cantAviones = Integer.parseInt(p.getProperty("cantAviones"));

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion = new Avion(p);
				
				if (i <= cantAviones / 2) {
					avion.setUbicacionY(depExplosivos.getUbicacionY() + (baseDistancia / 2 * i) + 10);
					avion.setUbicacionX(depCombustible.getUbicacionX());
					}
				else {
					avion.setUbicacionY(depCombustible.getUbicacionY() + (baseDistancia /2 * i) + 20);
					avion.setUbicacionX(depExplosivos.getUbicacionX());
				}
					
						
				avion.setSprite(avion.getSprite() + player1.getBando());

				aviones.add(avion);
			}

			int limiteCampo = Integer.parseInt(p.getProperty("limiteCampo"));

			ArrayList<Defensa> artillerias = new ArrayList<Defensa>();

			int cantArtilleria = cantAviones * Integer.parseInt(p.getProperty("multARTILLERIA"));
			for (int i = 1; i <= cantArtilleria; i++) {
				Defensa artilleria = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				if (i <= cantArtilleria / 2) {
					artilleria.setUbicacionX(
							artilleria.ubicacionInicial(
									limiteCampo - depCombustible.getUbicacionX() - baseDistancia,
									depCombustible.getUbicacionX() + baseDistancia
									));
					artilleria.setUbicacionY(
							artilleria.ubicacionInicial(
									depCombustible.getUbicacionY(), baseDistancia  + 20	//para que no quede arriba de la torreta
									));

				} else {
					artilleria.setUbicacionX(
							artilleria.ubicacionInicial(limiteCampo - depCombustible.getUbicacionX() - baseDistancia,
									depCombustible.getUbicacionX() + baseDistancia));
					artilleria.setUbicacionY(
							artilleria.ubicacionInicial(limiteCampo - depCombustible.getUbicacionY() - baseDistancia,
									depCombustible.getUbicacionY() + baseDistancia  + 20	//para que no quede arriba de la torreta
									));

				}
				
				artillerias.add(artilleria);
			}

			player1.setDepositoCombustible(depCombustible);
			player1.setDepositoExplosivos(depExplosivos);
			player1.setTorreta(torreta);
			player1.setAviones(aviones);
			player1.setArtillerias(artillerias);

			Provision depCombustible2 = new Provision(Provision.TIPO_PROVISION_COMBUSTIBLE, p);
			Provision depExplosivos2 = new Provision(Provision.TIPO_PROVISION_EXPLOSIVOS, p);
			Defensa torreta2 = new Defensa(Defensa.TIPO_DEFENSA_TORRETA, p);

			depCombustible2.setUbicacionX(depCombustible.getUbicacionX());
			depCombustible2.ubicacionEspejar(this.tamanioAncho, depCombustible.getUbicacionY());

			depExplosivos2.setUbicacionX(depExplosivos.getUbicacionX());
			
			depExplosivos2.ubicacionEspejar(this.tamanioAncho, depExplosivos.getUbicacionY());
			depExplosivos2.setSprite("DER" + depExplosivos2.getSprite());

			torreta2.setUbicacionX(torreta.getUbicacionX());
			torreta2.ubicacionEspejar(this.tamanioAncho, torreta.getUbicacionY());

			ArrayList<Avion> aviones2 = new ArrayList<Avion>();

			for (int i = 1; i <= cantAviones; i++) {
				Avion avion2 = new Avion(p);

				if (i <= cantAviones / 2)
				{																				
					avion2.setUbicacionY(depExplosivos2.getUbicacionY() + (baseDistancia / 2 * i) + 10);
					avion2.setUbicacionX(depCombustible2.getUbicacionX());
				}
				else
				{
					avion2.setUbicacionY(depCombustible2.getUbicacionY() + (baseDistancia / 2 * i) + 20);				
					avion2.setUbicacionX(depExplosivos2.getUbicacionX());
				}
				avion2.setSprite(avion2.getSprite() + player2.getBando());
				aviones2.add(avion2);
			}

			ArrayList<Defensa> artillerias2 = new ArrayList<Defensa>();

			for (int i = 1; i <= cantArtilleria; i++) {
				Defensa artilleria2 = new Defensa(Defensa.TIPO_DEFENSA_ARTILLERIA, p);

				Defensa atilleriaAux = artillerias.get(i - 1);
				artilleria2.setUbicacionX(atilleriaAux.getUbicacionX());
				artilleria2.ubicacionEspejar(this.tamanioAncho, atilleriaAux.getUbicacionY());
				artillerias2.add(artilleria2);

			}

			player2.setDepositoCombustible(depCombustible2);
			player2.setDepositoExplosivos(depExplosivos2);
			player2.setTorreta(torreta2);
			player2.setAviones(aviones2);
			player2.setArtillerias(artillerias2);

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
