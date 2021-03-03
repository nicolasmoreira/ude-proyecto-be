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
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.Fachada;
import com.ude.proyecto.logica.entidades.Combate;

@WebServlet("/rest/partida")
public class CrearPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CrearPartida() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties prop = new Properties();
		InputStream input = null;

		JsonObject json = new JsonObject();
		String player = null;
		String color = null;
		Combate combate = null;

		try {

			Fachada fachada = Fachada.getInstanceFachada();

			player = "Player1"; // a fuego por ahora

			color = request.getParameter("color");

			if (player != null && color != null) {
				input = getClass().getClassLoader().getResourceAsStream("config.properties");
				prop.load(input);

				fachada = Fachada.getInstanceFachada();

				if (fachada.getPartida() == null) {
					combate = fachada.iniciarPartida(player, color);
				} else {
					combate = fachada.getPartida();
				}

				json.add("partida", new Gson().toJsonTree(combate));
			} else {
				json.addProperty("mensaje", "Debe elegir un color de jugador.");
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