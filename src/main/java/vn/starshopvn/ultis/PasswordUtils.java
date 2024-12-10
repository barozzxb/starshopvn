package vn.starshopvn.ultis;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Mã hóa mật khẩu
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Kiểm tra mật khẩu đã mã hóa
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

