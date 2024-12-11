package vn.starshopvn.dao.impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.starshopvn.config.JPAConfig;
import vn.starshopvn.dao.CartDAO;
import vn.starshopvn.entity.Cart;
import vn.starshopvn.entity.CartItem;
import vn.starshopvn.entity.CartItemId;

public class CartDAOImpl implements CartDAO {

    @Override
    public Cart getCartByUserId(String userId) {
    	EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Cart> query = enma.createQuery("SELECT c FROM Cart c WHERE c.account.userid = :userId", Cart.class);
        query.setParameter("userId", userId);
        return query.getSingleResult() != null ? query.getSingleResult() : null;
//        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<CartItem> getCartItemsByCartId(String cartId) {
    	EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<CartItem> query = enma.createQuery("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId", CartItem.class);
        query.setParameter("cartId", cartId);
        return query.getResultList();
    }

    @Override
    public void addCartItem(CartItem cartItem) {
    	EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            enma.persist(cartItem);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
    	EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            enma.merge(cartItem);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeCartItem(String pid, String cartid) {
    	EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            CartItemId cartItemId = new CartItemId(cartid, pid);
            CartItem item = enma.find(CartItem.class, cartItemId);
            if (item != null) {
                enma.remove(item);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void clearCart(String cartId) {
    	EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            enma.createQuery("DELETE FROM CartItem ci WHERE ci.cart.cartId = :cartId")
              .setParameter("cartId", cartId)
              .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public boolean isProductInCart(String cartId, String productId) {
    	EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Long> query = enma.createQuery(
            "SELECT COUNT(ci) FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.product.pid = :productId",
            Long.class
        );
        query.setParameter("cartId", cartId);
        query.setParameter("productId", productId);
        return query.getSingleResult() > 0;
    }
    
    @Override
    public void createCart(Cart cart) {
    	EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            enma.persist(cart);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

	@Override
	public CartItem getItemInCart(String cartId, String productId) {
		EntityManager enma = JPAConfig.getEntityManager();
		CartItemId cartItemId = new CartItemId(cartId, productId);
        CartItem citem = enma.find(CartItem.class, cartItemId);
        return citem;
        
	}
}
