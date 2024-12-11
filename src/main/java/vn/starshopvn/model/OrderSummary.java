package vn.starshopvn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderSummary {
    
	private int year;
    private int month;
    private long totalCost;
    
}