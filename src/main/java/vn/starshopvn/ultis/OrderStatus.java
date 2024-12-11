package vn.starshopvn.ultis;

import java.util.ArrayList;
import java.util.List;

public class OrderStatus {

	public static final String ordered = "Đã đặt hàng";
	
	public static final String notPaidYet = "Chưa thanh toán";
	
	public static final String paid = "Đã thanh toán";
	
	public static final String shipping = "Đang giao hàng";
	
	public static final String received = "Đã nhận hàng";
	
	public static final String cancelled = "Đã hủy đơn";

	public List<String> getOrderStatus(){
		List<String> list = new ArrayList<>();
		list.add(cancelled);
		list.add(ordered);
		list.add(notPaidYet);
		list.add(paid);
		list.add(shipping);
		list.add(received);
		return list;
	}
}
