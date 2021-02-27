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
import com.ude.proyecto.logica.entidades.Jugador;

@WebServlet("/rest/partida")
public class CrearPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonObject json = new JsonObject();
	Combate combate = null;

	public CrearPartida() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Properties prop = new Properties();
		InputStream input = null;

		String player = null;
		String bando = null;

		try {
			

			Fachada fachada = Fachada.getInstanceFachada();

			player = "Player1"; // a fuego por ahora
			bando = Jugador.TIPO_BANDO_ROJO; // a fuego por ahora	
			System.out.println(bando);		
			// player = request.getParameter("player");
			// bando = request.getParameter("bando");

			if (player != null && bando!=null) {
				input = getClass().getClassLoader().getResourceAsStream("config.properties");
				prop.load(input);

				fachada = Fachada.getInstanceFachada();
				
				if (this.combate == null) {
					player = "Player1"; // a fuego por ahora
					bando = Jugador.TIPO_BANDO_ROJO; // a fuego por ahora
					this.combate = fachada.iniciarPartida(player, bando);
				}
				else	//seria player 2 sin problemas
				{
					this.combate = fachada.getPartida();
				}

				
				json.add("partida", new Gson().toJsonTree(combate));
			} else {
				json.addProperty("mensaje", "Debe elegir un bando de jugador.");
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