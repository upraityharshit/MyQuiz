package conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.activation.*;

public class SendMailMessage {
	 
	final String senderEmailID= "oespaper9760@gmail.com";
	final String senderpassword="oesprocess";
	final String emailSMTPserver="smtp.gmail.com";
	final String emailServerPort="465";
	static String receiverEmailId;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String name;
	
	public SendMailMessage(String id,String subject,String message){
		
		try{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
			String str="select name,email from student where studentid=?";
			ps=con.prepareStatement(str);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next()){
				name=rs.getString(1).toUpperCase();
				this.receiverEmailId=rs.getString(2);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		
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
			
			msg.setSubject(subject);
			msg.setText("Dear  "+name+" \n\n\n\n" +message);
			msg.setFrom(new InternetAddress(senderEmailID));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmailId));
			Transport.send(msg);
			JOptionPane.showMessageDialog(null, "Mail Sent....");
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Check Internet Connection");
		}
	}
	
	public class SMTPAuthenticator extends javax.mail.Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(senderEmailID,senderpassword);
		}
	}
	

}
