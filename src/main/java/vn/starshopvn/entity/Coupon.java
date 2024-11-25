package vn.starshopvn.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Coupons")
@NamedQuery(name = "coupon.findAll", query = "select c from Coupon c")
public class Coupon implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cid", columnDefinition = "nvarchar(255)")
	private String cid;
	
	@Column(name = "cname", columnDefinition = "nvarchar(255)")
	private String cname;
	
	@Column(name = "cpercent", columnDefinition = "nvarchar(255)")
	private String cpercent;
	
	@Column(name = "start", columnDefinition = "timestamp")
	private Timestamp start;
	
	@Column(name = "end", columnDefinition = "timestamp")
	private Timestamp end;
}
