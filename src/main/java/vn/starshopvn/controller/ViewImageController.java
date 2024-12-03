package vn.starshopvn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.ultis.Constant;

/*
 * Use
	<c:url value="/image?fname=${prod.ppicture}" var="imgUrl"></c:url>
	<img src="{imgUrl}"/>
*/

@WebServlet(urlPatterns = "/image")
public class ViewImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");
        if (fileName == null || fileName.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename parameter is missing or invalid");
            return;
        }

        File file = new File(Constant.upDir + "/" + fileName);
        if (!file.exists() || !file.canRead()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found or no read permission");
            return;
        }

        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String mimeType = "image/jpeg";  // Default MIME type

        if ("png".equals(fileExtension)) {
            mimeType = "image/png";
        } else if ("gif".equals(fileExtension)) {
            mimeType = "image/gif";
        }

        resp.setContentType(mimeType);
        try (FileInputStream fis = new FileInputStream(file)) {
            IOUtils.copy(fis, resp.getOutputStream());
        }
    }
}
