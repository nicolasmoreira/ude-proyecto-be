package com.ude.proyecto.logica.colecciones;

import java.util.HashMap;
import java.util.Map;

import com.ude.proyecto.logica.entidades.Defensa;

public class Artillerias {
	
	private Map<Integer,Defensa> Artillerias;

	public Artillerias() {
		Artillerias = new HashMap<Integer,Defensa>();
	}
	
	public boolean member(int idC) {
		return Artillerias.containsKey(idC);
	}
	
	public void insert(Defensa a) {
		int idC = a.getIdComponente();
		Artillerias.put(idC, a);
	}
	
	public Defensa find(int idC) {
		return Artillerias.get(idC);
	}

	public void delete(int idC) {
		Artillerias.remove(idC);
	}

}
