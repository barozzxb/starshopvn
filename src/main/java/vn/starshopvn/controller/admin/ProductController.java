package vn.starshopvn.controller.admin;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.starshopvn.entity.Genre;
import vn.starshopvn.entity.Product;
import vn.starshopvn.service.GenreService;
import vn.starshopvn.service.ProductService;
import vn.starshopvn.service.impl.GenreServiceImpl;
import vn.starshopvn.service.impl.ProductServiceImpl;
import vn.starshopvn.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/products", "/admin/product/edit", "/admin/product/add", "/admin/product/delete" })
public class ProductController extends HttpServlet {

	ProductService pServ = new ProductServiceImpl();
	GenreService gServ = new GenreServiceImpl();

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		List<Genre> genres = gServ.findAll();
		if (url.contains("/products")) {
			String upPath = Constant.upDir;
			req.setAttribute("upPath", upPath);
			List<Product> listprod = pServ.findAll();
			req.setAttribute("listprod", listprod);
			req.setAttribute("listgenre", genres);
			req.getRequestDispatcher("/views/admin/product/products.jsp").forward(req, resp);
		}
		if (url.contains("admin/product/edit")) {
			String pid = req.getParameter("pid");
			Product p = pServ.findById(pid);
			req.setAttribute("prod", p);
			
			req.setAttribute("listgenre", genres);
			req.getRequestDispatcher("/views/admin/product/product-edit.jsp").forward(req, resp);
		} else {
			String pid = req.getParameter("pid");
			if (pServ.delete(pid)) {
				resp.sendRedirect(req.getContextPath() + "/admin/products");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

		String url = req.getRequestURI();
		if (url.contains("admin/product/add")) {
			String pid = req.getParameter("pid");
			String pname = req.getParameter("pname");
			int pquantity = Integer.parseInt(req.getParameter("pquantity"));
			int pprice = Integer.parseInt(req.getParameter("pprice"));
			String pinfo = req.getParameter("pinfo");
			String pgenre = req.getParameter("pgenre");

			Product prod = new Product();
			prod.setGenre(gServ.findById(pgenre));
			prod.setPid(pid);
			prod.setPinfo(pinfo);
			prod.setPname(pname);
			prod.setPprice(pprice);
			prod.setPquantity(pquantity);
			Timestamp createdat = new Timestamp(new Date().getTime());
			prod.setCreatedat(createdat);
			// Xu li hinh anh
//			String iName = "";
//			String type = gServ.findById(pgenre).getGname().toLowerCase().replace(",", "");
//			type = type.replace(",", "");
//			String upPath = Constant.upDir;
//			File upDir = new File(upPath);
//			if (!upDir.exists()) {
//				upDir.mkdir();
//			}
//			try {
//				Part p = req.getPart("ppicture");
//				if (p.getSize() > 0) {
//					String name = Paths.get(p.getSubmittedFileName()).getFileName().toString();
//					// Rename
//					int index = name.lastIndexOf(".");
//					String ext = name.substring(index + 1);
//					String lowername = pname.replace(" ", "").toLowerCase();
//					iName = pid + "_" + lowername + "." + ext;
//					// Write file to directory
//					p.write(upPath + "/" + iName);
//					prod.setPpicture(iName);
//
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}

			// Xử lý hình ảnh
			String iName = "";
			String type = gServ.findById(pgenre).getGname().toLowerCase().replace(",", "");
			type = type.replace(",", "");
			String upPath = Constant.upDir;
			File upDir = new File(upPath);
			if (!upDir.exists()) {
			    upDir.mkdir();
			}
			try {
			    Part p = req.getPart("ppicture");
			    if (p.getSize() > 0) {
			        String name = Paths.get(p.getSubmittedFileName()).getFileName().toString();
			        // Đổi tên file
			        int index = name.lastIndexOf(".");
			        String ext = name.substring(index + 1);
			        String lowername = pname.replace(" ", "").toLowerCase();
			        iName = pid + "_" + lowername + "." + ext;


			        BufferedImage originalImage = ImageIO.read(p.getInputStream());

			        int size = Math.min(originalImage.getWidth(), originalImage.getHeight());
			        int x = (originalImage.getWidth() - size) / 2;
			        int y = (originalImage.getHeight() - size) / 2;

			        BufferedImage croppedImage = originalImage.getSubimage(x, y, size, size);

			        int targetSize = 500;
			        BufferedImage resizedImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_RGB);
			        Graphics2D g2d = resizedImage.createGraphics();
			        g2d.drawImage(croppedImage, 0, 0, targetSize, targetSize, null);
			        g2d.dispose();

			        // Lưu ảnh vào thư mục
			        File outputFile = new File(upPath + "/" + iName);
			        ImageIO.write(resizedImage, ext.toLowerCase(), outputFile);

			        prod.setPpicture(iName);
			    }
			} catch (Exception e) {
			    e.printStackTrace();
			}

			if (pServ.insert(prod)) {
				resp.sendRedirect(req.getContextPath() + "/admin/products");
			}

		}
		if (url.contains("admin/product/edit")) {
			String pid = req.getParameter("pid");
			String pname = req.getParameter("pname");
			int pquantity = Integer.parseInt(req.getParameter("pquantity"));
			int pprice = Integer.parseInt(req.getParameter("pprice"));
			String pinfo = req.getParameter("pinfo");
			String pmanufacturer = req.getParameter("pmanufacturer");
			String pgenre = req.getParameter("pgenre");
			
			
			Product prod = new Product();
			prod.setPid(pid);
			prod.setGenre(gServ.findById(pgenre));
			prod.setPinfo(pinfo);
			prod.setPname(pname);
			prod.setPprice(pprice);
			prod.setPquantity(pquantity);
			
			String pimage = prod.getPpicture();
			
			// Xu li hinh anh
			String iName = "";
			String upPath = Constant.upDir;
			File upDir = new File(upPath);
			if (!upDir.exists()) {
				upDir.mkdir();
			}
			try {
				Part p = req.getPart("ppicture");
				if (p.getSize() > 0) {
					deleteFile(upPath + "\\" + pimage);
					String name = Paths.get(p.getSubmittedFileName()).getFileName().toString();
					// Rename
					int index = name.lastIndexOf(".");
					String ext = name.substring(index + 1);
					String lowername = pname.replace(" ", "").toLowerCase();
					iName = pid + "_" + lowername + "." + ext;
					// Write file to directory
					p.write(upPath + "/" + iName);
					prod.setPpicture(iName);
				}
					else if (req.getParameter("ppicture") != null) {
						prod.setPpicture(req.getParameter("ppicture"));
					}
					else {
						prod.setPpicture(pimage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (pServ.update(prod)) {
				resp.sendRedirect(req.getContextPath() + "/admin/products");
			}
		}
	}
	public static void deleteFile(String filePath) throws IOException {

		Path path = Paths.get(filePath);
		Files.delete(path);
	}

}
