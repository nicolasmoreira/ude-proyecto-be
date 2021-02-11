package com.ude.proyecto.persistencia.poolConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion implements IConexion {

	private Connection con;

	public Conexion(String url, String user, String password) throws SQLException {
		this.con = DriverManager.getConnection(url, user, password);
	}

	public Connection getConnection() {
		return con;
	}
}
