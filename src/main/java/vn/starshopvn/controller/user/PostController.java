package vn.starshopvn.controller.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Post;
import vn.starshopvn.service.PostService;
import vn.starshopvn.service.impl.PostServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/user/posts")
public class PostController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	 PostService postService = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy danh sách bài viết từ Service
        List<Post> posts = postService.findAll();

        // Đặt danh sách vào request attribute
        req.setAttribute("posts", posts);

        // Chuyển hướng tới JSP hiển thị bài viết
        req.getRequestDispatcher("/WEB-INF/views/posts.jsp").forward(req, resp);
    }
}