package vn.starshopvn.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "Genres")
@NamedQuery(name = "genre.findAll", query = "select g from Genre g")
public class Genre {
	@Id
	@Column(name="gid", columnDefinition = "NVARCHAR(255)")
	private String gid;
	
	@Column(name="gname", columnDefinition = "NVARCHAR(255)")
	private String gname;
	
	@Column(name="gdescription", columnDefinition = "NVARCHAR(MAX)")
	private String gdescription;
	
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
	
	@Transient
	public int getProductCount() {
	    return products == null ? 0 : products.size();
	}
}