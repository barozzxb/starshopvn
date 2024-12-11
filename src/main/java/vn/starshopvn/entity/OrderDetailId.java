package vn.starshopvn.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Embeddable
public class OrderDetailId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "oid", nullable = false, columnDefinition = "nvarchar(255)")
	private String oid;

	@Column(name = "pid", nullable = false, columnDefinition = "nvarchar(255)")
	private String pid;
}
