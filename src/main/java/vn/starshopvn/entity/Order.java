package vn.starshopvn.entity;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Orders")
@NamedQuery(name = "order.findAll", query = "select o from Order o")
public class Order {

	@Id
	@Column(name = "oid", columnDefinition = "nvarchar(255)")
	private String oid;
	
	@Column(name = "date", columnDefinition = "datetime2")
	private LocalDateTime date;
	
	@Column(name = "status", columnDefinition = "nvarchar(255)")
	private String status;
		
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
	
	@OneToMany(mappedBy = "odid.order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails;
}
