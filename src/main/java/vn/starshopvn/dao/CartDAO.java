package vn.starshopvn.dao;


import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;

public interface CartDAO {
    // Lấy giỏ hàng từ session
    Cart getCart();

    // Lưu giỏ hàng vào session
    void saveCart(Cart cart);

    // Thêm sản phẩm vào giỏ hàng
    void addItemToCart(CartItem item);

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    void updateItemQuantity(String productName, int quantity);

    // Xóa sản phẩm khỏi giỏ hàng
    void removeItem(String productName);

    // Tính tổng tiền giỏ hàng
    double calculateTotal();
}
