package com.ude.proyecto.persistencia.poolConexiones;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.ude.proyecto.persistencia.PersistenciaException;

public class PoolConexiones implements IPoolConexiones {

	private Conexion[] conexiones;
	private int creadas;
	private String driver;
	private int nivelTransaccionalidad;
	private String password;
	private int tamanio;
	private int tope;
	private String url;
	private String user;

	public PoolConexiones() throws PersistenciaException {
		try {
			Properties p = new Properties();
			String nomArch = "config.properties";
			p.load(new FileInputStream(nomArch));
			driver = p.getProperty("driver");
			url = p.getProperty("url");
			user = p.getProperty("user");
			password = p.getProperty("password");
			String aux1 = p.getProperty("nivelTransaccionalidad");
			nivelTransaccionalidad = Integer.valueOf(aux1);
			String aux2 = p.getProperty("tama�o");
			tamanio = Integer.valueOf(aux2);
			tope = 0;
			creadas = 0;
			conexiones = new Conexion[tamanio];

		} catch (FileNotFoundException e) {
			throw new PersistenciaException("Error archivo no encontrado.");
		} catch (IOException e) {
			throw new PersistenciaException("Error IOException.");
		}
	}

	@Override
	public synchronized void liberarConexion(IConexion icon, boolean ok) throws PersistenciaException {
		try {
			if (ok) {
				((Conexion) icon).getConnection().commit();
			} else {
				((Conexion) icon).getConnection().rollback();
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error al liberar conexion.");
		}
		conexiones[tope] = (Conexion) icon;
		this.tope++;
		notify();
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException {
		boolean ok = false;
		Conexion conexion = null;
		while (!ok) {
			if (tope > 0) {
				conexion = conexiones[tope - 1]; // xq va de 0 a 4 se pone tope -1
				this.tope--;
				ok = true;
			} else if (creadas < tamanio) // puedo crear
			{
				try {
					conexion = new Conexion(url, user, password);
					ok = true;
					creadas++;
				} catch (SQLException e) {
					throw new PersistenciaException("Error al crear.");
				}
			} else // mando el proceso a dormir
			{
				try {
					wait();
				} catch (InterruptedException e) {
					throw new PersistenciaException("Error sincronizacion.");
				}
			}
		}
		try {
			conexion.getConnection().setTransactionIsolation(nivelTransaccionalidad);
			conexion.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new PersistenciaException("Error de conexion.");
		}
		return conexion;
	}
}
