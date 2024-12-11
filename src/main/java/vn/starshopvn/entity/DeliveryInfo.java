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
	
	@Column(name = "dcountry", columnDefinition = "nvarchar(255)")
	private String dcountry;
	
	@Column(name = "dzipcode", columnDefinition = "nvarchar(255)")
	private String dzipcode;
	
	@Column(name = "dtype", columnDefinition = "nvarchar(255)")
	private String dtype;
	
	@ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Account account;
	
	public static DeliveryInfo parse(String input) {
	    DeliveryInfo deliveryInfo = new DeliveryInfo();
	    
	    String[] parts = input.split(", ");
	    
	    for (String part : parts) {
	        String[] keyValue = part.split("=");
	        if (keyValue.length == 2) {
	            String key = keyValue[0].trim();
	            String value = keyValue[1].trim();
	            
	            switch (key) {
	                case "deinfoid":
	                    deliveryInfo.setDeinfoid(value);
	                    break;
	                case "daddress":
	                    deliveryInfo.setDaddress(value);
	                    break;
	                case "ddistrict":
	                    deliveryInfo.setDdistrict(value);
	                    break;
	                case "dcity":
	                    deliveryInfo.setDcity(value);
	                    break;
	                case "dprovince":
	                    deliveryInfo.setDprovince(value);
	                    break;
	                case "dcountry":
	                    deliveryInfo.setDcountry(value);
	                    break;
	                case "dzipcode":
	                    deliveryInfo.setDzipcode(value);
	                    break;
	                case "dtype":
	                    deliveryInfo.setDtype(value);
	                    break;

	                case "account":
	                    // Giả sử bạn có một phương thức để parse Account instance từ string
	                    // deliveryInfo.setAccount(parseAccount(value));
	                    break;
	                default:
	                    // Nếu có trường khác không được định nghĩa thì bỏ qua
	                    break;
	            }
	        }
	    }
	    
	    return deliveryInfo;
	}

	@Override
	public String toString() {
	    return "DeliveryInfo{" +
	           "deinfoid='" + deinfoid + '\'' +
	           ", daddress='" + daddress + '\'' +
	           ", ddistrict='" + ddistrict + '\'' +
	           ", dcity='" + dcity + '\'' +
	           ", dprovince='" + dprovince + '\'' +
	           ", dcountry='" + dcountry + '\'' +
	           ", dzipcode='" + dzipcode + '\'' +
	           ", dtype='" + dtype + '\'' +
	           ", accountId='" + (account != null ? account.getUserid() : "null") + '\'' +
	           '}';
	}
}
