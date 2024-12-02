package vn.starshopvn.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderDetailId implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "oid", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "pid", nullable = false)
	private Product product;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailId that = (OrderDetailId) o;

        if (order != null ? !order.equals(that.order) : that.order != null) return false; 
        return product != null ? product.equals(that.product) : that.product == null;   
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0; 
        result = 31 * result + (product != null ? product.hashCode() : 0); 
        return result;
    }
}
