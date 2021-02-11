package com.ude.proyecto.persistencia.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ude.proyecto.logica.objetos.Avion;
import com.ude.proyecto.persistencia.PersistenciaException;
import com.ude.proyecto.persistencia.consultas.Consultas;
import com.ude.proyecto.persistencia.poolConexiones.Conexion;
import com.ude.proyecto.persistencia.poolConexiones.IConexion;

public class DAOAviones {

	public Avion find(IConexion icon, String cod) throws PersistenciaException {

		Consultas consulta = new Consultas();
		String query = consulta.darAvion();
		Conexion con = (Conexion) icon;
		PreparedStatement darAvion = null;
		ResultSet rs = null;
		Avion avion = null;

		try {

			darAvion = con.getConnection().prepareStatement(query);
			darAvion.setString(1, cod);
			rs = darAvion.executeQuery();

			if (rs.next()) {
				String codigo = rs.getString(1);
				float coordx = rs.getFloat(2);
				float coordy = rs.getFloat(3);
				boolean colision = rs.getBoolean(4);
				avion = new Avion(codigo, coordx, coordy, colision);
			}
			rs.close();
			darAvion.close();

		} catch (SQLException e) {
			// existeFolio.close();
			throw new PersistenciaException("Error en la persistencia");

		}

		return avion;
	}
}
