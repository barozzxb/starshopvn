package vn.starshopvn.entity;

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
@Table(name = "DeliveryInfos")
@NamedQuery(name = "DeliveryInfo.findAll", query = "select d from DeliveryInfo d")
public class DeliveryInfo {

	@Id
	@Column(name = "deinfoid", columnDefinition = "nvarchar(255)")
	private String deinfoid;
	
	@Column(name = "daddress", columnDefinition = "nvarchar(255)")
	private String daddress;
	
	@Column(name = "ddistrict", columnDefinition = "nvarchar(255)")
	private String ddistrict;
	
	@Column(name = "dcity", columnDefinition = "nvarchar(255)")
	private String dcity;
	
	@Column(name = "dprovince", columnDefinition = "nvarchar(255)")
	private String dprovince;
	
	@Column(name = "dzipcode", columnDefinition = "nvarchar(255)")
	private String dzipcode;
	
	@Column(name = "dtype", columnDefinition = "nvarchar(255)")
	private String dtype;
	
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
}
