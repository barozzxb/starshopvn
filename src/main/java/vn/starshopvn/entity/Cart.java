package vn.starshopvn.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    // Thêm sản phẩm vào giỏ hàng
    public void addItem(CartItem item) {
        for (CartItem cartItem : items) {
            if (cartItem.getProductName().equals(item.getProductName())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());  // Nếu sản phẩm đã có, tăng số lượng
                return;
            }
        }
        items.add(item);  // Nếu sản phẩm chưa có trong giỏ, thêm mới
    }

    // Lấy danh sách các sản phẩm trong giỏ hàng
    public List<CartItem> getItems() {
        return items;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeItem(String productName) {
        items.removeIf(item -> item.getProductName().equals(productName));
    }

    // Cập nhật số lượng sản phẩm trong giỏ
    public void updateQuantity(String productName, int quantity) {
        for (CartItem item : items) {
            if (item.getProductName().equals(productName)) {
                item.setQuantity(quantity);  // Cập nhật số lượng của sản phẩm
                return;
            }
        }
    }

    public double getTotalPrice() {
        double total = 0;

        // Kiểm tra nếu items không phải là null và có phần tử
        if (items != null && !items.isEmpty()) {
            for (CartItem item : items) {
                // Kiểm tra giá trị hợp lệ của price và quantity
                if (item.getPrice() > 0 && item.getQuantity() > 0) {
                    total += item.getPrice() * item.getQuantity();  // Tính tổng giá trị giỏ hàng
                }
            }
        }
        return total;  // Trả về tổng giá trị
    }
}
