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

import com.google.gson.JsonObject;
import com.ude.proyecto.logica.Fachada;

@WebServlet("/rest/salvar-partida")
public class SalvarPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalvarPartida() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Properties prop = new Properties();
		InputStream input = null;

		JsonObject json = new JsonObject();

		try {

			Fachada fachada = Fachada.getInstanceFachada();

			if (fachada.getPartida() != null) {
				input = getClass().getClassLoader().getResourceAsStream("config.properties");
				prop.load(input);

				fachada.salvarPartida(fachada.getPartida());
				json.addProperty("mensaje", "OK");
				response.setStatus(201);
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
