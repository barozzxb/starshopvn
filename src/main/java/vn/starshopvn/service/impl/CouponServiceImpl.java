package vn.starshopvn.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import vn.starshopvn.dao.CouponDAO;
import vn.starshopvn.dao.impl.CouponDAOImpl;
import vn.starshopvn.entity.Coupon;
import vn.starshopvn.service.CouponService;

public class CouponServiceImpl implements CouponService {

    CouponDAO couponDao = new CouponDAOImpl();

    @Override
    public List<Coupon> findAll() {
        return couponDao.findAll();
    }

    @Override
    public Coupon findById(String cid) {
        return couponDao.findById(cid);
    }

    @Override
    public boolean addCoupon(Coupon coupon) {
        if (couponDao.findById(coupon.getCid()) != null) {
            return false; // Coupon đã tồn tại
        }
        try {
            couponDao.addCoupon(coupon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        try {
            couponDao.updateCoupon(coupon);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCoupon(String cid) {
        try {
            couponDao.deleteCoupon(cid);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public double applyCoupon(String cid, double totalAmount) {
		 try {
	            Coupon coupon = couponDao.findById(cid);
	            if (coupon == null) {
	                return totalAmount; // Không tìm thấy mã giảm giá, giữ nguyên số tiền
	            }

	            // Kiểm tra thời gian áp dụng mã giảm giá
	            LocalDateTime now = LocalDateTime.now();
	            if (coupon.getStart().isAfter(now) || coupon.getEnd().isBefore(now)) {
	                return totalAmount; // Mã giảm giá hết hạn
	            }

	            // Tính số tiền giảm giá
	            double discountPercent = Double.parseDouble(coupon.getCpercent());
	            double discountAmount = totalAmount * discountPercent / 100;
	            return totalAmount - discountAmount;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return totalAmount; // Nếu xảy ra lỗi, giữ nguyên số tiền
	        }
	}

   
    
}
