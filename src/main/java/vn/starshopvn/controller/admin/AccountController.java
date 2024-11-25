package vn.starshopvn.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

@WebServlet(urlPatterns = {"/admin/accounts", "/admin/account/delete", 
		"/admin/account/setDeactive", "/admin/account/setActive"})
public class AccountController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	AccountService aServ = new AccountServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if(url.contains("accounts")) {
			List<Account> listacc = aServ.findAll();
			req.setAttribute("accounts", listacc);
			req.getRequestDispatcher("/views/admin/accountmgmt/accounts.jsp").forward(req, resp);
		} else
			if(url.contains("account/setDeactive")) {
				String userid = req.getParameter("uid");
				if (aServ.setDeactive(userid)) {
					req.getRequestDispatcher("/admin/accounts").forward(req, resp);
				}
			}else if (url.contains("account/setActive")) {
					String userid = req.getParameter("uid");
					if (aServ.setActive(userid)) {
						req.getRequestDispatcher("/admin/accounts").forward(req, resp);
					}
				}
			else if (url.contains("account/delete")) {
				String userid = req.getParameter("uid");
				if (aServ.deleteById(userid)) {
					req.getRequestDispatcher("/admin/accounts").forward(req, resp);
				}
			}
	}
}
