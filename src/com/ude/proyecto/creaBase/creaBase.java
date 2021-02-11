package com.ude.proyecto.creaBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.ude.proyecto.logica.vo.VOAvion;
//import logica.objetos.Avion;

public class creaBase {

	public static void main(String[] args) {

		try {
			Properties p = new Properties();
			String nomArch = "config.properties";
			p.load(new FileInputStream(nomArch));

			/* Cargo dinamicamente el driver de MySQL */
			String driver = p.getProperty("driver");
			Class.forName(driver);

			/* Una vez cargado el driver, me conecto con la base de datos */
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");

			Connection con = DriverManager.getConnection(url, user, password);

			String creaBase;
			Statement stmt;
			int cant;

			/* borro base de datos */
			creaBase = "DROP DATABASE IF EXISTS Juego";
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();

			/* Creo la base de datos */

			creaBase = "Create database Juego";
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases creadas");
			System.out.println("Estamos con conexion a MYSQL :)");

			/* Selecciono base */
			creaBase = "Use Juego";
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases seleccionadas");

			/* Creo tabla avion */

			String creaTabla = "create table Avion (codigo VARCHAR(60) primary key, coordX float, coordY float, colision boolean)";
			Statement stmt2 = con.createStatement();
			cant = stmt2.executeUpdate(creaTabla);
			stmt2.close();
			System.out.print("Resultado de " + creaTabla + ": ");
			System.out.println(cant + " tablas creadas");

			/* Inserto los registros iniciales */
			String creareg = "insert into Avion values ('Matias-3',5,5,0)";
			Statement stmt4 = con.createStatement();
			cant = stmt4.executeUpdate(creareg);
			creareg = "insert into Avion values ('Matias-2',100,100,0)";
			cant = stmt4.executeUpdate(creareg);
			stmt4.close();

			// prueba select de datos

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Avion");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				float coordX = rs.getFloat("coordX");
				float coordY = rs.getFloat("coordY");
				boolean colision = rs.getBoolean("colision");
				VOAvion VOA = new VOAvion(coordX, coordY, colision);
				System.out.println(VOA.toString());
			}
			rs.close();
			pstmt.close();

			/* Cierro la conexion con la base de datos */
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
