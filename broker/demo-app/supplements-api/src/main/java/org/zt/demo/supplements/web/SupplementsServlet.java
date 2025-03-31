package org.zt.demo.supplements.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zt.demo.supplements.SupplementsRepository;

import com.google.gson.Gson;

@WebServlet("/supplements")
public class SupplementsServlet extends HttpServlet {
	private Gson gson = new Gson();
	private SupplementsRepository supplementsRepository = new SupplementsRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.printf("GET %s from %s%n", request.getRequestURI(), request.getRemoteAddr());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonResponse = gson.toJson(supplementsRepository.getSupplements());
		response.getWriter().write(jsonResponse);
	}
}
