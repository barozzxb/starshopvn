package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.dao.PostDAO;
import vn.starshopvn.dao.impl.PostDAOImpl;
import vn.starshopvn.entity.Post;

@WebServlet(urlPatterns = { "/user/posts" })
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PostDAO postDAO = new PostDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> posts = postDAO.findAll();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/views/user/post/post-list.jsp").forward(req, resp);
    }
}
