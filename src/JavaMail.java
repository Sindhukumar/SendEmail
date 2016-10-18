
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class JavaMail {
	public static void sendMail(String to, String from, String subject, String body, boolean bodyisHTML)
			throws MessagingException {
		Properties props = new Properties();

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", 8081);
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.quitwait", "false");
		// NOTE: Session object is part of javax.mail.Session
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
		session.setDebug(true);

		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if (bodyisHTML) {
			message.setContent(body, "text/html");
		} else {
			message.setText(body);
		}

		System.out.println(body);

		Address fromAddress = new InternetAddress(from);
		Address toAddress = new InternetAddress(to);
		message.setFrom(fromAddress);
		message.setRecipient(Message.RecipientType.TO, toAddress);

		Transport transport = session.getTransport();
		transport.connect();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();

	}

}
