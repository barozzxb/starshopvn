package vn.starshopvn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CartItem")
public class CartItem {
    @EmbeddedId
    private CartItemId cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid", insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    private Product product;

    @Column(name = "quantity", columnDefinition = "int")
    private int quantity;
}
