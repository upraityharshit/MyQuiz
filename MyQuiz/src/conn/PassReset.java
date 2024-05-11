package conn;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import conn.SendMail.SMTPAuthenticator;

public class PassReset {
	 
		final String senderEmailID= "oespaper9760@gmail.com";
		final String senderpassword="oesprocess";
		final String emailSMTPserver="smtp.gmail.com";
		final String emailServerPort="465";
		static String receiverEmailId;
		
		public PassReset(String receiverEmailId,String id,String ipass){
			this.receiverEmailId=receiverEmailId;
			
			Properties properties=new Properties();
			properties.put("mail.smtp.user",senderEmailID);
			properties.put("mail.smtp.host",emailSMTPserver);
			properties.put("mail.smtp.port",emailServerPort);
			properties.put("mail.smtp.starttls.enable","true");
			properties.put("mail.smtp.auth","true");
			properties.put("mail.smtp.socketFactory.port",emailServerPort);
			properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback","false");
			SecurityManager security=System.getSecurityManager();
			try{
				Authenticator auth=new SMTPAuthenticator();
				Session session=Session.getInstance(properties,auth);
				MimeMessage msg=new MimeMessage(session);
				
				msg.setSubject("Password Reset");
				msg.setText("\nYour Password is Changed"
						+ "\n\n Your User Id is "+id
						+ "\n New Password is "+ipass
						+ "\n\n\nThank You");
				msg.setFrom(new InternetAddress(senderEmailID));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailId));
				Transport.send(msg);
				System.out.println("Message Sent.....");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public class SMTPAuthenticator extends javax.mail.Authenticator{
			public PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(senderEmailID,senderpassword);
			}
		}
}
