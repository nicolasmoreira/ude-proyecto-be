package com.ude.proyecto.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.Fachada;
import com.ude.proyecto.logica.entidades.Combate;



@WebServlet("/rest/salvarpartida")
public class SalvarPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SalvarPartida() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties prop = new Properties();
		InputStream input = null;

		JsonObject json = new JsonObject();
		
		Combate combate = null;

		try {

			Fachada fachada = Fachada.getInstanceFachada();
			String nombrePartida = "NombreAFuego";

			
			if (nombrePartida != null && fachada.getPartida() != null) {
				input = getClass().getClassLoader().getResourceAsStream("config.properties");
				prop.load(input);
				
				combate = fachada.getPartida();			
				json.add("partida", new Gson().toJsonTree(combate));
				fachada.salvarPartida(nombrePartida, json.toString());
				json.addProperty("mensaje", "OK");	
				
			} else {
				json.addProperty("mensaje", "Debe elegir nombre para guardar la partida.");
				response.setStatus(500);
			}
		} catch (Exception e) {
			json.addProperty("mensaje", e.getMessage());
			response.setStatus(500);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}

