package com.ude.proyecto.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ude.proyecto.logica.Fachada;

@WebServlet("/rest/cargar-partida")
public class CargarPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CargarPartida() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JsonObject json = new JsonObject();

		try {
			Fachada fachada = Fachada.getInstanceFachada();

			int codigo = Integer.parseInt(request.getParameter("codigo"));

			fachada.cargarPartida(codigo);

			json.add("partida", new Gson().toJsonTree(fachada.getPartida()));
			response.setStatus(200);
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
