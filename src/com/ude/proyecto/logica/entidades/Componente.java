package com.ude.proyecto.logica.entidades;

import java.util.Properties;

public abstract class Componente {
	
	//autonumerico
	public static int idAutonumerico = 0;
		
	//atributos
	private int idComponente;
	private String tipoComponente;
	private float ubicacionX;
	private float ubicacionY;
	private float rotacion;
	private int vida;
	private String sprite;
	private String sonido;

	public Componente() {
		
	}
	
	public Componente(int idComponente, String tipoComponente, float ubicacionX, float ubicacionY,float rotacion, int vida,
			String sprite, String sonido) {
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

	//constructor para tipo de componente
	public Componente(String tipoComponente, Properties p) {
		
		this.idComponente = Componente.idAutonumerico ++;		
		this.tipoComponente = tipoComponente;
		
		this.rotacion = Float.parseFloat(p.getProperty(tipoComponente.trim().toLowerCase() + "Rotacion")); // 0;
		this.vida = Integer.parseInt(p.getProperty(tipoComponente.trim().toLowerCase() + "Vida"));//1;
		
//		/*
//		switch(tipoComponente)
//		{
//		   // declaración case
//		   // los valores deben ser del mismo tipo de la expresión
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

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public String getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public float getUbicacionX() {
		return ubicacionX;
	}

	public void setUbicacionX(float ubicacionX) {
		this.ubicacionX = ubicacionX;
	}

	public float getUbicacionY() {
		return ubicacionY;
	}

	public void setUbicacionY(float ubicacionY) {
		this.ubicacionY = ubicacionY;
	}
	
	public float getRotacion() {
		return rotacion;
	}

	public void setRotacion(float rotacion) {
		this.rotacion = rotacion;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}
	
}
