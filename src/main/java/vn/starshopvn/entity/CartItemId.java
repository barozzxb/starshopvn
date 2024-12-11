package vn.starshopvn.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class CartItemId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "cid", columnDefinition = "nvarchar(255)")
    private String cartId;

    @Column(name = "pid", columnDefinition = "nvarchar(255)")
    private String pid;
}
