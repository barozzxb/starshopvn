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
import vn.starshopvn.ultis.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/user/posts", "/user/post/edit", "/user/post/add", "/user/post/delete" })
public class PostController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	PostService poServ = new PostServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/posts")) {
			String upPath = Constant.upDir;
			req.setAttribute("upPath", upPath);
			List<Post> listprod = poServ.findAll();
			req.setAttribute("listprod", listprod);
			req.getRequestDispatcher("/views/user/posts.jsp").forward(req, resp);
		}
		if (url.contains("admin/product/edit")) {
			String pid = req.getParameter("pid");
//			Product p = pServ.findById(pid);
//			req.setAttribute("prod", p);
			req.getRequestDispatcher("views/user/post-edit.jsp").forward(req, resp);
		} else {
			String pid = req.getParameter("pid");
//			if (pServ.delete(pid)) {
//				resp.sendRedirect(req.getContextPath() + "/user/posts");
//			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");

//		String url = req.getRequestURI();
//		if (url.contains("admin/product/add")) {
//			String pid = req.getParameter("pid");
//			String pname = req.getParameter("pname");
//			int pquantity = Integer.parseInt(req.getParameter("pquantity"));
//			int pprice = Integer.parseInt(req.getParameter("pprice"));
//			String pinfo = req.getParameter("pinfo");
//			String pmanufacturer = req.getParameter("pmanufacturer");
//			String pgenre = req.getParameter("pgenre");
//
//			Product prod = new Product();
//			prod.setGenre(gServ.findById(pgenre));
//			prod.setPid(pid);
//			prod.setPinfo(pinfo);
//			prod.setPname(pname);
//			prod.setPprice(pprice);
//			prod.setPquantity(pquantity);
//			
//			// Xu li hinh anh
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
//
//			if (pServ.insert(prod)) {
//				resp.sendRedirect(req.getContextPath() + "/admin/products");
//			}
//
//		}
//		if (url.contains("admin/product/edit")) {
//			String pid = req.getParameter("pid");
//			String pname = req.getParameter("pname");
//			int pquantity = Integer.parseInt(req.getParameter("pquantity"));
//			int pprice = Integer.parseInt(req.getParameter("pprice"));
//			String pinfo = req.getParameter("pinfo");
//			String pmanufacturer = req.getParameter("pmanufacturer");
//			String pgenre = req.getParameter("pgenre");
//			
//			
//			Product prod = new Product();
//			prod.setPid(pid);
//			prod.setGenre(gServ.findById(pgenre));
//			prod.setPinfo(pinfo);
//			prod.setPname(pname);
//			prod.setPprice(pprice);
//			prod.setPquantity(pquantity);
//			
//			String pimage = prod.getPpicture();
//			
//			// Xu li hinh anh
//			String iName = "";
//			String upPath = Constant.upDir;
//			File upDir = new File(upPath);
//			if (!upDir.exists()) {
//				upDir.mkdir();
//			}
//			try {
//				Part p = req.getPart("ppicture");
//				if (p.getSize() > 0) {
//					deleteFile(upPath + "\\" + pimage);
//					String name = Paths.get(p.getSubmittedFileName()).getFileName().toString();
//					// Rename
//					int index = name.lastIndexOf(".");
//					String ext = name.substring(index + 1);
//					String lowername = pname.replace(" ", "").toLowerCase();
//					iName = pid + "_" + lowername + "." + ext;
//					// Write file to directory
//					p.write(upPath + "/" + iName);
//					prod.setPpicture(iName);
//				}
//					else if (req.getParameter("ppicture") != null) {
//						prod.setPpicture(req.getParameter("ppicture"));
//					}
//					else {
//						prod.setPpicture(pimage);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			if (pServ.update(prod)) {
//				resp.sendRedirect(req.getContextPath() + "/admin/products");
//			}
//		}
//	}
//	public static void deleteFile(String filePath) throws IOException {
//
//		Path path = Paths.get(filePath);
//		Files.delete(path);
	}
}