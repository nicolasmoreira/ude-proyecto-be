package com.ude.proyecto.persistencia.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOCombate {

	private String password;
	private String url;
	private String user;

	public DAOCombate(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;

	}

	public String cargarCombate(int codigo) {

		// levanto el combate
		String query = "SELECT partida from DAOCombate WHERE codigo = ?";
		String combateStr = "";

		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, codigo);

			ResultSet rs = pstmt.executeQuery();
			rs.next();

			combateStr = rs.getString("partida");
			
			System.out.println(combateStr);

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return combateStr;
	}

	public void guardarCombate(String nombrePartida, String partida) {
		String query = "INSERT INTO DAOCombate (nombre_partida, partida)" + "VALUES (?,?)";

		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, nombrePartida);
			pstmt.setString(2, partida);

			pstmt.execute();

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<HashMap> listarCombates() {
		ArrayList<HashMap> combates = new ArrayList<>();

		String query = "SELECT codigo, nombre_partida FROM DAOCombate ORDER BY codigo DESC LIMIT 5";

		Connection con;
		try {
			con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				HashMap<String, String> combate = new HashMap<String, String>();
				combate.put("nombre_partida", rs.getString("nombre_partida"));
				combate.put("codigo", rs.getString("codigo"));
				combates.add(combate);
			}

			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return combates;
	}
}
