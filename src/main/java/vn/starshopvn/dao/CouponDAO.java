package vn.starshopvn.dao;

import vn.starshopvn.entity.Coupon;
import java.util.List;

public interface CouponDAO {

    List<Coupon> findAll();

    Coupon findById(String cid);

    void addCoupon(Coupon coupon);

    void updateCoupon(Coupon coupon);

    void deleteCoupon(String cid)throws Exception;
}
