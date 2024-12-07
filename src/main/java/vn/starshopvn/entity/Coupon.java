package vn.starshopvn.entity;

import java.io.Serializable;

import java.time.LocalDateTime;

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
	
	@Column(name = "startDate", columnDefinition = "datetime2")
	private LocalDateTime start;
	
	@Column(name = "endDate", columnDefinition = "datetime2")
	private LocalDateTime end;
}
