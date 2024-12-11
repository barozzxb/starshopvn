package vn.starshopvn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.starshopvn.entity.Product;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TopSellingProduct {
	
	Product product;
	long ordernumber;
}
