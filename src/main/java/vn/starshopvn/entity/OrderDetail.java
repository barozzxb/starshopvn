package vn.starshopvn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderDetailId odid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid", insertable = false, updatable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    private Product product;
    
    @Column(name = "dquantity")
    private int dquantity;

    @Column(name = "dprice")
    private double dprice;
}
