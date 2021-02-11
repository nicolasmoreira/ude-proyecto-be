package com.ude.proyecto.logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import com.ude.proyecto.logica.objetos.Avion;
import com.ude.proyecto.logica.vo.VOAvion;
import com.ude.proyecto.persistencia.PersistenciaException;
import com.ude.proyecto.persistencia.daos.DAOAviones;
import com.ude.proyecto.persistencia.poolConexiones.IConexion;
import com.ude.proyecto.persistencia.poolConexiones.IPoolConexiones;

public class Fachada {

	// private Avion p;
	// private IConexion icon;
	private IPoolConexiones ipool;

	public Fachada() throws RemoteException {
		Properties p = new Properties();
		String nomArch = "config.properties";
		try {
			p.load(new FileInputStream(nomArch));
			String driver = p.getProperty("driver");
			Class.forName(driver);
			String poolConcreto = p.getProperty("nombreClase");
			ipool = (IPoolConexiones) Class.forName(poolConcreto).newInstance();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void borrar(VOAvion voP) {

	}

	public VOAvion darCoordenadasOrigen(String cod) throws PersistenciaException {
		// cod = "Matias-2"; //lo cambio a fuego
		String msgError = null;
		IConexion icon = null;
		// boolean noExisteAvion = false;
		VOAvion voavion = null;
		try {
			icon = ipool.obtenerConexion(true);

			DAOAviones DAOA = new DAOAviones();
			Avion avion = DAOA.find(icon, cod);

			// System.out.println(" error " + avion.toString());

			if (avion != null) {
				voavion = new VOAvion(avion.getCoordX(), avion.getCoordY(), avion.hayColision());
			} else {
				// noExisteAvion = true;
				msgError = "Avion no existe";
			}
			ipool.liberarConexion(icon, true);

		} catch (PersistenciaException e) {
			System.out.println(" error " + e.darMensaje());
			throw new PersistenciaException(msgError);
		}

		return voavion;

	}

	public boolean hayColision(VOAvion voPA, VOAvion voPB) {
		return false;
	}

}
