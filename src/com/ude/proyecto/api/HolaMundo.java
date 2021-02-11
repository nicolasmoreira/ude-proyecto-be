package com.ude.proyecto.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/rest/join")

public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JsonObject json = new JsonObject();

	public HolaMundo() {
		super();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nickName = null;
		nickName = request.getParameter("nick_name");

		if (nickName != null) {
			// Fachada fachada = Fachada.getInstanceFachada();
			try {
				// json = fachada.unirsePartida(nickName);
				json.addProperty("mensaje", "Hola mundo");
				response.setStatus(200);
			} catch (Exception e) {
				json.addProperty("mensaje", e.getMessage());
				response.setStatus(500);
			}
		} else {
			json.addProperty("mensaje", "El nick_name es requerido");
			response.setStatus(500);
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}