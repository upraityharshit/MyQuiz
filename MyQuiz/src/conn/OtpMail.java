package conn;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OtpMail {
	public OtpMail(String receiver,int otp,String callpage){
			
			String sender="oespaper9760@gmail.com";
			String pass="oesprocess";
				
			Properties prop=new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.user",sender);
			prop.put("mail.smtp.starttls.enable","true");
			prop.put("mail.smtp.auth","true");
			prop.put("mail.smtp.socketFactory.port",465);
			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.socketFactory.fallback","false");
				
			try{
				Session session= Session.getDefaultInstance(prop,null);
				session.setDebug(true);
				MimeMessage message= new MimeMessage(session);
				if(callpage.equals("ResetPassword")){
					message.setSubject("OES Reset Password Process");
					message.setText("Conformation of your ID to Password reset"
							+ "\n\nYour OTP(one time password) is "+otp
							+ "\n\n\nThank You");
				}
				else{
					message.setSubject("OES Registration Process");
					message.setText("Conformation for your Registration"
							+ "\n\nYour OTP(one time password) is "+otp
							+ "\n\n\nThank You");
				}
				message.setFrom(new InternetAddress(sender));
				message.addRecipient(RecipientType.TO, new InternetAddress(receiver));
				message.saveChanges();
				Transport transport=session.getTransport("smtp");
				transport.connect("smtp.gmail.com",sender,pass);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();

				System.out.println("Mail Send Successfully");
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}
}
