package vn.starshopvn.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Post;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.PostService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.PostServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;

@WebServlet(urlPatterns = {"/home"})
public class WebController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	GenreService genreServ = new GenreServiceImpl();
	ProductService prodServ = new ProductServiceImpl();
	PostService postSer=new PostServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Genre> genres = genreServ.findAll();
		req.setAttribute("genres", genres);
		 List<Post> posts=postSer.findAll();
         req.setAttribute("posts", posts);
		List<Product> newprods = prodServ.top3new();
		req.setAttribute("topprod", newprods);
		req.getRequestDispatcher("/views/guest/home.jsp").forward(req, resp);
	}
}
