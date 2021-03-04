package com.ude.proyecto.persistencia.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ude.proyecto.logica.entidades.Combate;

public class DAOCombate {

	private String url;
	private String user;
	private String password;
	
	public DAOCombate(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		
	}

	public void guardarCombate(String nombrePartida, String partida) {
		String query = 	"INSERT INTO DAOCombate (codigo, nombre_partida, partida)" + 
						"VALUES (?,?,?)";
		
		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(query);
			
			//pstmt.setInt(1, j.getId());
			pstmt.setString(2, nombrePartida);
			pstmt.setString(3, partida);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String cargarCombate(int idCombate, String nombre_partida) {
		
		// creo el combate
		String query = "SELECT codigo, nombre_partida, partida from DAOCombate WHERE codigo = ? and nombre_partida = ?"; 
		String Combate = "";	
		
		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, idCombate);
			pstmt.setString(2, nombre_partida);
			
			ResultSet rs = pstmt.executeQuery();			
			rs.next();
			
			Combate = rs.getString("partida");
			
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Combate;
	}
}
