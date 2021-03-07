package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public abstract class Componente {

	public static int idAutonumerico = 0;

	protected int idComponente;
	protected float rotacion;
	protected String sonido;
	protected String sprite;
	protected String tipoComponente;
	protected float ubicacionX;
	protected float ubicacionY;
	protected int vida;
	
	public static final String TIPO_PROVISION = "PROVISION";
	public static final String TIPO_DEFENSA = "DEFENSA";
	
	public Componente() {
		super();
	}

	public Componente(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY, float rotacion,
			int vida, String sprite, String sonido) {
		super();
		this.idComponente = idComponente;
		this.tipoComponente = tipoComponente;
		this.ubicacionX = ubicacionX;
		this.ubicacionY = ubicacionY;
		this.rotacion = rotacion;
		this.vida = vida;
		this.sprite = sprite;
		this.sonido = sonido;
	}

	public Componente(String tipoComponente, Properties p) {

		this.idComponente = Componente.idAutonumerico++;
		this.tipoComponente = tipoComponente;

		this.rotacion = Float.parseFloat(p.getProperty(tipoComponente.trim().toLowerCase() + "Rotacion")); // 0;
		this.vida = Integer.parseInt(p.getProperty(tipoComponente.trim().toLowerCase() + "Vida"));// 1;

		int margen = Integer.parseInt(p.getProperty("margen"));// 1;
		int verticeX = Integer.parseInt(p.getProperty("verticeX"));// 1;
		int verticeY = Integer.parseInt(p.getProperty("verticeY"));// 1;

		this.ubicacionX = ubicacionInicial(margen, verticeX);
		this.ubicacionY = ubicacionInicial(margen, verticeY);

		// System.out.print("this.ubicacionX " + String.valueOf(this.ubicacionX) +
		// "this.ubicacionY " + String.valueOf(this.ubicacionY));

//		/*
//		switch(tipoComponente)
//		{
//		   // declaraci�n case
//		   // los valores deben ser del mismo tipo de la expresi�n
//		   case "Provision" :			    
//			  /* this.idComponente = 1;
//			   this.ubicacionX = 100;
//			   this.ubicacionY = 100;*/
//			   this.rotacion = Integer.parseInt(p.getProperty(tipoComponente + "Rotacion")); // 0;
//			   this.vida = Integer.parseInt(p.getProperty(tipoComponente + "Vida"));//1;
//			 //  this.sprite = "DepositoCombustible";
//			 //  this.sonido = "DepositoCombustible";	   
//			   
//			   break;
//		   
//		   case "Defensa" :
//			  // this.idComponente = 1;
//			  // this.ubicacionX = 150;
//			  // this.ubicacionY = 150;
//			   this.rotacion = 0;
//			   this.vida = 1;
//			  // this.sprite = "Defensa";
//			  // this.sonido = "Defensa";	   
//			   	      
//			   break; 
//		
//		   case "Avion" :
//			  // this.idComponente = 1;
//			  // this.ubicacionX = 150;
//			  // this.ubicacionY = 150;
//			   this.rotacion = 0;
//			  // this.vida = 5;
//			   this.sprite = "Avion";
//			   this.sonido = "Avion";	   
//			   	      
//			   break; 
//	   
//			   
//		   default : 
//		      // Declaraciones
//		}		
	}

	public int getIdComponente() {
		return idComponente;
	}

	public float getRotacion() {
		return rotacion;
	}

	public String getSonido() {
		return sonido;
	}

	public String getSprite() {
		return sprite;
	}

	public String getTipoComponente() {
		return tipoComponente;
	}

	public float getUbicacionX() {
		return ubicacionX;
	}

	public float getUbicacionY() {
		return ubicacionY;
	}

	public int getVida() {
		return vida;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public void setUbicacionX(float ubicacionX) {
		this.ubicacionX = ubicacionX;
	}

	public void setUbicacionY(float ubicacionY) {
		this.ubicacionY = ubicacionY;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public void ubicacionEspejar(int tamanioPantalla, float ubicacionY) {
		// la forma de espejar es restando al tamanio de la pantalla la ubicacion X
		// solamente
		this.ubicacionX = tamanioPantalla - this.ubicacionX;
		this.ubicacionY = ubicacionY;
	}

	public float ubicacionInicial(float margen, float veritce) {
		// calcula un random en un area cuadrada a partir de un vertice (x e y) inicial,
		// por ejemplo x=100 y=100
		return (int) (Math.random() * margen + veritce); // para que no se construlla en campo enemigo
		// https://programandoointentandolo.com/2013/10/como-generar-numeros-aleatorios-en-java.html#:~:text=La%20formula%20para%20obtener%20un,%3D%20(int)(Math.
	}

}
