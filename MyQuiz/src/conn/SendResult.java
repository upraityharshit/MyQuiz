package conn;
import java.sql.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class SendResult {
	 
	final String senderEmailID= "oespaper9760@gmail.com";
	final String senderpassword="oesprocess";
	final String emailSMTPserver="smtp.gmail.com";
	final String emailServerPort="465";
	static String receiverEmailId;
	Connection con;
	PreparedStatement ps,ps1;
	ResultSet rs,rs1;
	String name,result,topic;
	public SendResult(String studentid,int resultid){
		
		try{
			DatabaseConnect obj=new DatabaseConnect();
			con=obj.Connect();
			
			String str="select name,email from student where studentid=?";
			ps=con.prepareStatement(str);
			ps.setString(1, studentid);
			rs=ps.executeQuery();
			if(rs.next()){
				name=rs.getString(1).toUpperCase();
				this.receiverEmailId=rs.getString(2).toString();
			}
			
			String stp="select topicid,result from result where resultid=?";
			ps1=con.prepareStatement(stp);
			ps1.setInt(1, resultid);
			rs1=ps1.executeQuery();
			if(rs1.next()){
				topic=rs1.getString(1);
				result=rs1.getString(2);
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
			
			if(result.equals("Pass")){
				msg.setSubject("Congratulation!");
				msg.setText("Dear  "+name +"\n\n\n\nYou are Successfully cleared our exam OES(Online Examination System)"
					+ "\nYour Result ID is "+resultid
					+ "\nYour Subject is " +topic
					+ "\nFinal Result " +result
					+ "\n\n\nRegards\nOES Exam");
			}
			else{
				msg.setSubject("Thank You! For Attending this Paper");
				msg.setText("Dear  "+name +"\n\n\n\nSorry to inform you that!.. You are not Cleared this Exam"
					+ "\n\n\nRegards\nOES Exam");
			}
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
