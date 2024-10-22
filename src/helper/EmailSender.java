package helper;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void sendOtpEmail(String recipientEmail, String otp) {
		String from = "khronal777@gmail.com";
		String host = "smtp.gmail.com";

		// Cài đặt cấu hình SMTP
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Tạo phiên đăng nhập
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("khronal777@gmail.com", "nufq axlb ifzy ebdq");
			}
		});

		try {
			// Tạo message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
			message.setSubject("Your OTP Code");
			message.setText("Your OTP code is: " + otp);

			// Gửi email
			Transport.send(message);
			System.out.println("OTP email sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
