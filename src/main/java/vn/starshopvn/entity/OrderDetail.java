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

    @Column(name = "dquantity")
    private int dquantity;  // Số lượng sản phẩm trong đơn hàng

    @Column(name = "dprice")
    private double dprice;  // Giá của sản phẩm trong đơn hàng
}
