package com.ude.proyecto.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.ude.proyecto.logica.objetos.Avion;

public class Probandooooooooo {

	public static void main(String[] args) {

		try {
			Properties p = new Properties();
			String nomArch = "config.properties";
			p.load(new FileInputStream(nomArch));
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Estamos con conexion a MYSQL :)");

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM mapa");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				float coordX = rs.getFloat("coordX");
				float coordY = rs.getFloat("coordY");
				Avion pr = new Avion(coordX, coordY);
				// float equis = pr.getCoordX();
				// float y = pr.getCoordY();
				// System.out.println(equis);
				// System.out.println(y);
			}
			rs.close();
			pstmt.close();
			// con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("ERROR al cargar el controlador.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ERROR en la conexion a la base de datos.");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR para encontrar properties.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR IO.");
			e.printStackTrace();
		}
	}

}
