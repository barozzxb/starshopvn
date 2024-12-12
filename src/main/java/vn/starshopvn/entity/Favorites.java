package vn.starshopvn.entity;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Favorites")
@NamedQuery(name="favorite.findAll", query="select f from Favorites f")
public class Favorites implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "fid", columnDefinition = "nvarchar(255)")
    private String fid;
	
	@ManyToOne 
	@JoinColumn(name = "userid", nullable = false) 
	private Account account;
	
	@ManyToOne 
	@JoinColumn(name = "pid", nullable = false) 
	private Product product;
	
	@Override
	public String toString() {
	    return "Favorites{" +
	            "fid='" + fid + '\'' +
	            ", accountId=" + (account != null ? account.getUserid() : "null") +
	            ", productId=" + (product != null ? product.getPid() : "null") +
	            '}';
	}


}