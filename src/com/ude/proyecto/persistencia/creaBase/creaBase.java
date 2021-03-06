package com.ude.proyecto.persistencia.creaBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//import com.ude.proyecto.logica.vo.VOAvion;
//import logica.objetos.Avion;

public class creaBase {

	public static void main(String[] args) {
		//Properties p = new Properties();
		InputStream input = null;
		try {
			//Properties p = new Properties();
			//String nomArch = "config.properties";
			//p.load(new FileInputStream("config.properties"));
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

			/* borro base de datos */
			creaBase = "DROP DATABASE IF EXISTS " + database;
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();

			/* Creo la base de datos */

			creaBase = "Create database " + database; //  Juego";
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases creadas");
			System.out.println("Estamos con conexion a MYSQL :)");

			/* Selecciono base */
			creaBase = "Use " + database;// Juego";
			stmt = con.prepareStatement(creaBase);
			cant = stmt.executeUpdate(creaBase);
			stmt.close();
			System.out.print("Resultado de " + creaBase + ": ");
			System.out.println(cant + " bases seleccionadas");

			/* Creo tabla DAOCombate */

			String creaTabla = "create table DAOCombate (codigo int (3) AUTO_INCREMENT primary key, nombre_partida VARCHAR(255), partida TEXT)";
			Statement stmt2 = con.createStatement();
			cant = stmt2.executeUpdate(creaTabla);
			stmt2.close();
			System.out.print("Resultado de " + creaTabla + ": ");
			System.out.println(cant + " tablas creadas");

			/* Inserto los registros iniciales */
//			String creareg = "insert into Avion values ('Matias-3',5,5,0)";
//			Statement stmt4 = con.createStatement();
//			cant = stmt4.executeUpdate(creareg);
//			creareg = "insert into Avion values ('Matias-2',100,100,0)";
//			cant = stmt4.executeUpdate(creareg);
//			stmt4.close();

			// prueba select de datos

//			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Avion");
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				float coordX = rs.getFloat("coordX");
//				float coordY = rs.getFloat("coordY");
//				boolean colision = rs.getBoolean("colision");
//				VOAvion VOA = new VOAvion(coordX, coordY, colision);
//				System.out.println(VOA.toString());
//			}
//			rs.close();
//			pstmt.close();

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
