package vn.starshopvn.ultis;

import java.util.Random;

public class OTPUtils {
    // Hàm tạo mã OTP ngẫu nhiên
    public static String generateOTP(int length) {
        String characters = "0123456789";
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            otp.append(characters.charAt(random.nextInt(characters.length())));
        }
        return otp.toString();
    }
}

