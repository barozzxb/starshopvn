package vn.starshopvn.controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;


@WebServlet(urlPatterns = {"/user/cart.html"})
public class CartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy giỏ hàng từ session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Gửi giỏ hàng đến trang JSP (cart.jsp)
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/views/user/carts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        if ("add".equals(action)) {
            // Thêm sản phẩm vào giỏ hàng
            String productName = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            CartItem newItem = new CartItem(productName, price, quantity);

            boolean itemExists = false;
            for (CartItem item : cart.getItems()) {
                if (item.getProductName().equals(newItem.getProductName())) {
                    item.setQuantity(item.getQuantity() + newItem.getQuantity());
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                cart.addItem(newItem);
            }
        } else if ("remove".equals(action)) {
            // Xóa sản phẩm khỏi giỏ hàng
            String productName = request.getParameter("productName");
            cart.removeItem(productName);
        } else if ("update".equals(action)) {
            // Cập nhật số lượng sản phẩm
            String productName = request.getParameter("productName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            for (CartItem item : cart.getItems()) {
                if (item.getProductName().equals(productName)) {
                    item.setQuantity(quantity);
                    break;
                }
            }
        }
        // Lưu giỏ hàng vào session
        session.setAttribute("cart", cart);

        // Chuyển hướng về trang giỏ hàng
        response.sendRedirect(request.getContextPath() + "/user/cart.html");
    }
}
