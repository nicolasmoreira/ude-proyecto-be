package com.ude.proyecto.persistencia.creaBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class creaBase {

	public static void main(String[] args) {
		InputStream input = null;
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("src/config.properties"));

			String driver = p.getProperty("db_driver");
			String host = p.getProperty("db_server");
			String port = p.getProperty("db_port");
			String database = p.getProperty("db_database");

			String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?serverTimezone=UTC";
			String user = p.getProperty("db_user");
			String password = p.getProperty("db_password");
			Class.forName(driver);

			Connection con = DriverManager.getConnection(url, user, password);

			String creaBase;
			Statement stmt;
			int cant;

			creaBase = "DROP DATABASE IF EXISTS " + database;
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();

			creaBase = "Create database " + database;
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases creadas");
			System.out.println("Estamos con conexion a MYSQL :)");

			creaBase = "Use " + database;
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases seleccionadas");

			String creaTabla = "create table DAOCombate (codigo int (3) AUTO_INCREMENT primary key, nombre_partida VARCHAR(255), partida TEXT)";
			Statement stmt2 = con.createStatement();
			cant = stmt2.executeUpdate(creaTabla);
			stmt2.close();
			System.out.print("Resultado de " + creaTabla + ": ");
			System.out.println(cant + " tablas creadas");
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
