package vn.starshopvn.service;

import java.util.List;

import vn.starshopvn.entity.Coupon;

public interface CouponService {
	List<Coupon> findAll();

    Coupon findById(String cid);

    boolean addCoupon(Coupon coupon);

    boolean updateCoupon(Coupon coupon);

    boolean deleteCoupon(String cid)throws Exception;
    public double applyCoupon(String cid, double totalAmount);
}
