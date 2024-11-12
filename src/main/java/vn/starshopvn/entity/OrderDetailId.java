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
}
