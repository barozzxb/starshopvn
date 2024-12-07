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
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Accounts")
@NamedQuery(name = "Account.findAll", query = "select a from Account a where a.role.roleid = 2")
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	private Role role;
	
	@Column(name = "createdat", columnDefinition = "timestamp")
	private Timestamp createdat;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<DeliveryInfo> deliveryinfos;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Order> orders;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Favorites> favorites;
	
	public Account(String userid, String password, @Email(message = "Email must be in the right form") String email) {
		super();
		this.userid = userid;
		this.password = password;
		this.email = email;
	}

	public Account(String userid, String password, @Email(message = "Email must be in the right form") String email,
			boolean isDeactivated, Role role, Timestamp createdat) {
		super();
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.isDeactivated = isDeactivated;
		this.role = role;
		this.createdat = createdat;
	}
	
	@Override
	public String toString() {
	    return "Account{" +
	            "userid='" + userid + '\'' +
	            ", name='" + name + '\'' +
	            ", email='" + email + '\'' +
	            ", gender='" + gender + '\'' +
	            ", address='" + address + '\'' +
	            ", isDeactivated=" + isDeactivated +
	            ", role=" + (role != null ? role.getRoleid() : "null") +
	            ", createdat=" + createdat +
	            ", postsCount=" + (posts != null ? posts.size() : 0) +
	            ", deliveryInfosCount=" + (deliveryinfos != null ? deliveryinfos.size() : 0) +
	            ", ordersCount=" + (orders != null ? orders.size() : 0) +
	            ", favoritesCount=" + (favorites != null ? favorites.size() : 0) +
	            '}';
	}

}