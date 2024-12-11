package vn.starshopvn.ultis;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailUltis {

public static void sendOTPEmail(String toEmail, String otp) throws MessagingException, UnsupportedEncodingException {
    	
    	if (toEmail == null || !isValidEmail(toEmail)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    	
    	// Cấu hình SMTP server
        String host = "smtp.gmail.com";  // Sử dụng SMTP của Gmail nếu bạn không sử dụng tên miền riêng
        String fromEmail = "nguyenbaoanpl321@gmail.com";  // Địa chỉ email đã thay đổi
        String fromName = "StarShop Support Team";  // Tên người gửi
        String password = "uczn naas nowg ljph";   // Mật khẩu email

        // Cấu hình các thuộc tính cho server SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Tạo Session để kết nối với server
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo đối tượng email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8")); // Thêm tên người gửi
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("OTP Verification - Mã OTP kích hoạt tài khoản");                 

     // Tạo nội dung email với thẻ HTML
        String emailContent = 
            "<html>" +
            "<body>" +
            "<p>Đây là tin nhắn tự động, bạn không cần trả lời email này!</p>" + 
            "<p>Mã OTP của bạn là: <strong>" + otp + "</strong></p>" +
            "</body>" +
            "</html>";

        // Đặt nội dung email dưới dạng HTML
        message.setContent(emailContent, "text/html; charset=UTF-8");

        // Gửi email
        Transport.send(message);
        System.out.println("Email sent successfully!");
    }
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
