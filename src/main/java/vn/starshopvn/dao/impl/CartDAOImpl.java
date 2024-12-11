package vn.starshopvn.dao.impl;

import vn.starshopvn.dao.CartDAO;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

public class CartDAOImpl implements CartDAO {

    private HttpSession session;

    public CartDAOImpl(HttpSession session) {
        this.session = session;
    }

    @Override
    public Cart getCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();  // Nếu không có giỏ hàng trong session, tạo giỏ hàng mới
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public void saveCart(Cart cart) {
        session.setAttribute("cart", cart);
    }

    @Override
    public void addItemToCart(CartItem item) {
        Cart cart = getCart();  // Lấy giỏ hàng hiện tại từ session
        cart.addItem(item);  // Thêm sản phẩm vào giỏ hàng
        saveCart(cart);  // Lưu lại giỏ hàng vào session
    }

    @Override
    public void updateItemQuantity(String productName, int quantity) {
        Cart cart = getCart();  // Lấy giỏ hàng hiện tại từ session
        cart.updateQuantity(productName, quantity);  // Cập nhật số lượng sản phẩm
        saveCart(cart);  // Lưu lại giỏ hàng vào session
    }

    @Override
    public void removeItem(String productName) {
        Cart cart = getCart();  // Lấy giỏ hàng hiện tại từ session
        cart.removeItem(productName);  // Xóa sản phẩm khỏi giỏ hàng
        saveCart(cart);  // Lưu lại giỏ hàng vào session
    }

    @Override
    public double calculateTotal() {
    	Cart cart = getCart();  // Lấy giỏ hàng hiện tại từ session
        return cart.getTotalPrice();  // Tính tổng tiền giỏ hàng (gọi phương thức getTotalPrice)
    }
}

