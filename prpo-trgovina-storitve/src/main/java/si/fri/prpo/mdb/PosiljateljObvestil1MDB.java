package si.fri.prpo.mdb;

import java.util.Properties;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Message-Driven Bean implementation class for: PosiljateljObvestil1MDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:jboss/jms/topics/ProgressTopic"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "java:jboss/jms/topics/ProgressTopic")
public class PosiljateljObvestil1MDB implements MessageListener {

	private final String username = "prpo2015@gmail.com";
	private final String password = "2015prpo";
	
    /**
     * Default constructor. 
     */
    public PosiljateljObvestil1MDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(javax.jms.Message message) {
    	
    	Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		
		try {
			
			Transport t = session.getTransport();
			Message ms = new MimeMessage(session);
			ms.setFrom(new InternetAddress("prpo2015@gmail.com"));
			InternetAddress[] to = new InternetAddress[] {  
                    new InternetAddress("prpo2015@gmail.com"),
                    new InternetAddress("naum_gj@hotmail.com")
            };  
			ms.setRecipients(javax.mail.Message.RecipientType.TO, to);
			ms.setSubject("Sporocilo o napredku");
			ms.setContent(message.getBody(String.class), "text/plain");
			
			t.connect();
			t.sendMessage(ms, to);

			
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }

}
