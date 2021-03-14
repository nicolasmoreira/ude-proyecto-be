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

@WebServlet("/rest/partidas")
public class ObtenerPartidas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ObtenerPartidas() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JsonObject json = new JsonObject();

		try {
			Fachada fachada = Fachada.getInstanceFachada();

			json.add("partidas", new Gson().toJsonTree(fachada.listarPartidas()));
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
