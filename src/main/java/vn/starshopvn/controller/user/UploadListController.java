package vn.starshopvn.controller.user;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user/uploads")
public class UploadListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đường dẫn tới thư mục upload
        String uploadPath = getServletContext().getRealPath("") + "uploads" + File.separator;
        File uploadDir = new File(uploadPath);

        // Lấy danh sách file
        if (uploadDir.exists() && uploadDir.isDirectory()) {
            File[] files = uploadDir.listFiles();
            req.setAttribute("files", files);
            System.out.println("Upload directory: " + uploadPath);

        } else {
            req.setAttribute("message", "Upload folder not found!");
        }

        // Chuyển đến trang JSP để hiển thị danh sách file
        req.getRequestDispatcher("/views/uploads/list.jsp").forward(req, resp);
    }
}

