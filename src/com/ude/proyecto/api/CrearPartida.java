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
	JsonObject json = new JsonObject();

	public CrearPartida() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties prop = new Properties();
		InputStream input = null;

		String player = null;

		try {

			Fachada fachada = Fachada.getInstanceFachada();

			player = "player 1"; // a fuego por ahora

			// player = request.getParameter("player");

			if (player != null) {
				input = getClass().getClassLoader().getResourceAsStream("config.properties");
				prop.load(input);

				fachada = Fachada.getInstanceFachada();

				Combate combate = fachada.iniciarPartida();
				json.add("partida", new Gson().toJsonTree(combate));
			} else {
				json.addProperty("mensaje", "Debe elegir un Player de jugador.");
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