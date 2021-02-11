package com.ude.proyecto.testing;

import java.rmi.RemoteException;

import com.google.gson.Gson;
import com.ude.proyecto.logica.Fachada;
import com.ude.proyecto.logica.vo.VOAvion;
import com.ude.proyecto.persistencia.PersistenciaException;

public class TestingFachada {

	public static void main(String[] args) throws RemoteException, PersistenciaException {
		// TODO Auto-generated method stub
		Fachada fachada = new Fachada(); // Fachada.getInstanceFachada();

		String cod = "Matias-2";

		VOAvion VOA = fachada.darCoordenadasOrigen(cod);

		// imprimo clase VOAvion
		System.out.println(" imprimo clase VOAvion " + VOA.toString());

		Gson gson = new Gson();

		String tmp = gson.toJson(VOA);

		// imprimo json VOAvion
		System.out.println(" imprimo json VOAvion " + tmp.toString());
	}

}
