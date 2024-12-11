package vn.starshopvn.service;

import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;

public interface CartService {
    // Lấy giỏ hàng hiện tại
    Cart getCart();

    // Thêm sản phẩm vào giỏ hàng
    void addItemToCart(CartItem item);

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    void updateItemQuantity(String productName, int quantity);

    // Xóa sản phẩm khỏi giỏ hàng
    void removeItem(String productName);

    // Tính tổng giá trị giỏ hàng
    double calculateTotal();
}
