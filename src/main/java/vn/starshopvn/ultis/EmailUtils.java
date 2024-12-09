package vn.starshopvn.ultis;

import javax.mail.*;
import javax.mail.internet.*;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.json.JSONObject;

public class EmailUtils {
    public static void sendOTPEmail(String toEmailj, String otp) throws MessagingException, UnsupportedEncodingException {
    	
    	String toEmail = extractEmail(toEmailj);
    	
    	if (toEmail == null || !isValidEmail(toEmail)) {
            throw new IllegalArgumentException("Invalid email address");
        }
    	
    	// Cấu hình SMTP server (sử dụng Gmail trong ví dụ này)
        String host = "smtp.elasticemail.com";  // Sử dụng SMTP của Gmail nếu bạn không sử dụng tên miền riêng
        String fromEmail = "nguyenbaoanpl321@gmail.com";  // Địa chỉ email đã thay đổi
        String fromName = "StarShop Support";  // Tên người gửi
        String password = "ED0EA82814F6844ACD086A47A3B32D0C475D";   // Mật khẩu email

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

     // Tạo nội dung email với thẻ HTML
        String emailContent = 
            "<html>" +
            "<body>" +
            "<p>This is an automated message, please do not reply!</p>" + 
            "<p>Your OTP code is: " + otp + "</p>" +
            "<p>You don't want to receive messages? Please click on this link:</p>" +
            "<p>If you want to subscribe to receive information from StarShop, please click the button below:</p>" +
            "<p><a href='https://api.elasticemail.com/contact/subscribe?version=2&pID=241ccd77-d299-4562-a136-4719b16bb4b5' " +
            "style='display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-align: center; " +
            "text-decoration: none; border-radius: 5px;'>Resubscribe to StarShop</a></p>" + 
            "<p>You don't want to receive messages? Please click on this link:</p>" +
            "</body>" +
            "</html>";

        // Đặt nội dung email dưới dạng HTML
        message.setContent(emailContent, "text/html; charset=UTF-8");

        // Gửi email
        Transport.send(message);
        System.out.println("Email sent successfully!");
    }
    private static String extractEmail(String jsonEmail) {
        try {
        	
        	JSONObject jsonObject = new JSONObject(jsonEmail);
            // Lấy giá trị của trường "email" trong JSON
            return jsonObject.getString("email");
        } catch (Exception e) {
            // Nếu gặp lỗi khi tách email, trả về null
            return null;
        }
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
}
