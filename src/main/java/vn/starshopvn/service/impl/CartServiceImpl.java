package vn.starshopvn.service.impl;

import vn.starshopvn.dao.CartDAO;
import vn.starshopvn.dao.impl.CartDAOImpl;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;
import vn.starshopvn.service.CartService;

import jakarta.servlet.http.HttpSession;

public class CartServiceImpl implements CartService {
    private CartDAO cartDAO;

    // Constructor, nhận HttpSession và khởi tạo CartDAO
    public CartServiceImpl(HttpSession session) {
        this.cartDAO = new CartDAOImpl(session);
    }

    @Override
    public Cart getCart() {
        return cartDAO.getCart();  // Lấy giỏ hàng từ DAO
    }

    @Override
    public void addItemToCart(CartItem item) {
        cartDAO.addItemToCart(item);  // Thêm sản phẩm vào giỏ hàng thông qua DAO
    }

    @Override
    public void updateItemQuantity(String productName, int quantity) {
        cartDAO.updateItemQuantity(productName, quantity);  // Cập nhật số lượng sản phẩm
    }

    @Override
    public void removeItem(String productName) {
        cartDAO.removeItem(productName);  // Xóa sản phẩm khỏi giỏ hàng
    }

    @Override
    public double calculateTotal() {
        return cartDAO.calculateTotal();  // Tính tổng giá trị giỏ hàng
    }
}
