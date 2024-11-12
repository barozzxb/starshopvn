package vn.starshopvn.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Order {

	@Id
	@Column(name = "oid", columnDefinition = "nvarchar(255)")
	private String oid;
	
	@Column(name = "date", columnDefinition = "timestamp")
	private Timestamp date;
	
	@Column(name = "status", columnDefinition = "nvarchar(255)")
	private String status;
		
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailId> orderDetails;
}
