package vn.starshopvn.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Accounts")
@NamedQuery(name = "Account.findAll", query = "select a from Account a where a.roleId = 2")
public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid", columnDefinition = "nvarchar(255)")
	private String userid;
	
	@Column(name = "password", columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "dob", columnDefinition = "nvarchar(255)")
	private String dob;
	
	@Column(name = "gender", columnDefinition = "nvarchar(15)")
	private String gender;
	
	@Column(name = "email", columnDefinition = "nvarchar(255)")
	@Email(message = "Email must be in the right form")
	private String email;
	
	@Column(name = "phonenum", columnDefinition = "nvarchar(255)")
	private String phonenum;
	
	@Column(name = "address", columnDefinition = "nvarchar(255)")
	private String address;
	
	@Column(name = "isDeactivated", columnDefinition = "boolean")
	private boolean isDeactivated;
	
	@Column(name = "roleId", columnDefinition = "int")
	private Integer roleId;
	
	@Column(name = "createdat", columnDefinition = "timestamp")
	private Timestamp createdat;
	
	public Account(String userid, String password, @Email(message = "Email must be in the right form") String email,
			Integer roleId) {
		super();
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
	}
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DeliveryInfo> deliveryinfos;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders;
}