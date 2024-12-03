package vn.starshopvn.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Products")
@NamedQuery(name="product.findAll", query="select p from Product p")
@ToString(exclude = {"genre", "orderDetails"})  // Loại trừ genre và orderDetails khỏi phương thức toString()
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pid", columnDefinition = "nvarchar(255)")
    private String pid;
    
    @Column(name = "pname", columnDefinition = "nvarchar(255)")
    private String pname;
    
    @Column(name = "pquantity")
    private int pquantity;
    
    @Column(name = "pprice")
    private int pprice;
    
    @Column(name = "pinfo", columnDefinition = "nvarchar(5000)")
    private String pinfo;
    
    @Column(name = "ppicture", columnDefinition = "nvarchar(5000)")
    private String ppicture;
    
    @Column(name = "prating")
    private int prating;

    @Column(name = "createdat", columnDefinition = "timestamp")
    private Timestamp createdat;
    
    @ManyToOne
    @JoinColumn(name = "gid", nullable = false)
    private Genre genre;

    @OneToMany(mappedBy = "odid.product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
    
    @OneToMany(mappedBy= "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Favorites> favorite;
}
