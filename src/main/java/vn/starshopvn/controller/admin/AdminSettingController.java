package vn.starshopvn.controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

@WebServlet(urlPatterns = {"/admin/settings", "/admin/settings/profile", "/admin/settings/change-password", "/admin/settings/delete",})
public class AdminSettingController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	AccountService service = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session != null && session.getAttribute("account") != null) 
    	{
        req.getRequestDispatcher("/views/admin/settings.jsp").forward(req, resp);
	    } else {
	        resp.sendRedirect(req.getContextPath() + "/login");
	    }
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		HttpSession session = req.getSession();
		
		String url = req.getRequestURI();
		if(url.contains("settings/profile")) {
			//String userid = req.getParameter("userid");
			String name = req.getParameter("name");
			String dob = req.getParameter("dob");
			String email = req.getParameter("email");
			String phonenum = req.getParameter("phonenum");
			String gender = req.getParameter("gender");
			String address = req.getParameter("address");
			
	
			Account acc = (Account)session.getAttribute("account");
			
			acc.setAddress(address);
			acc.setDob(dob);
			acc.setEmail(email);
			acc.setGender(gender);
			acc.setName(name);
			acc.setPhonenum(phonenum);
			
			
			String alertMsg = "";
			boolean updated = service.update(acc);
			if (updated == true) {
				alertMsg = "Successfully updated Account info";
				req.setAttribute("alert", alertMsg);
				resp.sendRedirect(req.getContextPath() + "/admin/settings");
			} else {
				alertMsg = "Error occurred!";
				req.setAttribute("alert", alertMsg);
				resp.sendRedirect(req.getContextPath() + "/admin/settings");
			}
		}
	}
}