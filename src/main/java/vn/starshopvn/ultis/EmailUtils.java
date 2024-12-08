package vn.starshopvn.ultis;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
public class EmailUtils {
    public static void sendOTPEmail(String toEmail, String otp) throws MessagingException, UnsupportedEncodingException {
        // Cấu hình SMTP server (sử dụng Gmail trong ví dụ này)
        String host = "smtp.gmail.com";  // Sử dụng SMTP của Gmail nếu bạn không sử dụng tên miền riêng
        String fromEmail = "your-mail@gmail.com";  // Địa chỉ email đã thay đổi
        String fromName = "StarShop Support";  // Tên người gửi
        String password = "your pass";   // Mật khẩu email

        // Cấu hình các thuộc tính cho server SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Tạo Session để kết nối với server
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo đối tượng email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8")); // Thêm tên người gửi
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);

        // Gửi email
        Transport.send(message);
    }
}
