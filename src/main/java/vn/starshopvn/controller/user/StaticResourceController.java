package vn.starshopvn.controller.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/user/services", "/user/about", "/user/contact"})
public class StaticResourceController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("services")) {
			req.getRequestDispatcher("/views/other/services.jsp").forward(req, resp);
		} else if (url.contains("about")) {
			req.getRequestDispatcher("/views/other/about-us.jsp").forward(req, resp);
		} else if (url.contains("contact")) {
			req.getRequestDispatcher("/views/other/contact.jsp").forward(req, resp);
		}
	}
}
