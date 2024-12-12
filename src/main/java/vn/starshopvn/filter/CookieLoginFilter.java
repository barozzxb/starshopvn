package vn.starshopvn.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Account;
import vn.starshopvn.service.AccountService;
import vn.starshopvn.service.impl.AccountServiceImpl;

@WebFilter("/*")
public class CookieLoginFilter implements Filter {

	AccountService aServ = new AccountServiceImpl();
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        // Nếu session đã có account, bỏ qua kiểm tra cookie
        if (session.getAttribute("account") != null) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userid")) {
                    String userId = cookie.getValue();
                    // Gọi hàm lấy thông tin user từ DB dựa trên userId
                    Account account = aServ.findById(userId);

                    if (account != null) {
                        session.setAttribute("account", account);
                    }
                    break;
                }
            }
        }

        chain.doFilter(request, response);
    }
}