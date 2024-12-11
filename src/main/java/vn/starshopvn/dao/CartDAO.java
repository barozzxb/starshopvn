package vn.starshopvn.dao;

import java.util.List;

import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;

public interface CartDAO {

	boolean isProductInCart(String cartId, String productId);

	void clearCart(String cartId);

	void removeCartItem(String pid, String cartid);

	void updateCartItem(CartItem cartItem);

	void addCartItem(CartItem cartItem);

	List<CartItem> getCartItemsByCartId(String cartId);

	Cart getCartByUserId(String userId);

	void createCart(Cart cart);
	
	CartItem getItemInCart(String cartId, String productId);

}
