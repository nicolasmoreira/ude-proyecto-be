package com.ude.proyecto.persistencia.poolConexiones;

import com.ude.proyecto.persistencia.PersistenciaException;

public interface IPoolConexiones {

	public void liberarConexion(IConexion icon, boolean ok) throws PersistenciaException;

	public IConexion obtenerConexion(boolean modifica) throws PersistenciaException;
}
