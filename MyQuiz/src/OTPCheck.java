import java.awt.*;
import java.awt.*;
import javax.swing.*;
import conn.DatabaseConnect;
import conn.OtpMail;
import conn.SendMail;
import java.awt.event.*;
import java.sql.*;

public class OTPCheck extends Thread {

	public JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OTPCheck window = new OTPCheck(23456,"demo","demo","demo","abc123@gmail.com","1234567890","01/01/1980","male","good");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String Email;
	int OTP;
	StringBuffer mail;
	String studentid,Pass,Name,Phone,Dob,Gender,Remarks;
	int resendtime;
	JLabel lblWaitForSec;
	JButton btnResendOtp;
	Thread t=new Thread(this);
	public OTPCheck(int otp,String sid,String pass,String name,String email,String phone,String dob,String gender,String remarks) {
		studentid=sid;
		Pass=pass;
		Name=name;
		Phone=phone;
		Dob=dob;
		Gender=gender;
		Remarks=remarks;
		
		OTP=otp;
		Email=email;
		mail=new StringBuffer(Email);
		int a=Email.indexOf("@");
		mail.setCharAt(a-1, '*');
		mail.setCharAt(a-2, '*');
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(-8,0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter the OTP to Verify Your Account");
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPleaseEnterThe.setBounds(401, 160, 641, 31);
		frame.getContentPane().add(lblPleaseEnterThe);
		
		JLabel lblAOtponeTime = new JLabel("A OTP(one time Password) has been sent to "+mail);
		lblAOtponeTime.setForeground(Color.GREEN);
		lblAOtponeTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAOtponeTime.setBounds(447, 223, 793, 25);
		frame.getContentPane().add(lblAOtponeTime);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(377, 210, 688, 2);
		frame.getContentPane().add(separator);
		
		textField = new JTextField();
		textField.setBounds(447, 296, 312, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(OTP==Integer.parseInt(textField.getText())){
						Connection con;
						PreparedStatement st;
						DatabaseConnect obj=new DatabaseConnect();
						con=(Connection) obj.Connect();
						String str="insert into student values(?,?,?,?,?,?,?,?)";
						st=con.prepareStatement(str);
						st.setString(1,studentid);
						st.setString(2,Pass);
						st.setString(3,Name);
						st.setString(4,Email);
						st.setString(5,Phone);
						st.setString(6,Dob);
						st.setString(7,Gender);
						st.setString(8,Remarks);
						int q=st.executeUpdate();		
						con.close();
						
						JOptionPane.showMessageDialog(frame, "Registration Successfull...\nThank You");
						new SendMail(Email,studentid,Pass);
						Student window = new Student("insert","user");
						window.frame.setVisible(true);
						frame.hide();
					}
					else{
						JOptionPane.showMessageDialog(frame, "OTP is not Matched");
					}
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(frame,"Check Internet Connection");
					e.printStackTrace();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame,"There are Some issue....try again");
					e.printStackTrace();
				}
			}
		});
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setBackground(new Color(255, 69, 0));
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnConfirm.setBounds(795, 293, 177, 33);
		frame.getContentPane().add(btnConfirm);
		
		btnResendOtp = new JButton("Resend OTP");
		btnResendOtp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new OtpMail(Email,OTP,"Student");
				
			}
		});
		btnResendOtp.setBackground(new Color(240, 240, 240));
		btnResendOtp.setBounds(647, 400, 151, 31);
		frame.getContentPane().add(btnResendOtp);
		btnResendOtp.setEnabled(false);
		
		t.start();
		lblWaitForSec = new JLabel("");
		lblWaitForSec.setForeground(Color.RED);
		lblWaitForSec.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWaitForSec.setBounds(650, 373, 206, 25);
		frame.getContentPane().add(lblWaitForSec);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(OTPCheck.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel.setBounds(0, 0, 1382, 738);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
	private JLabel lblNewLabel;
	public void run(){
		int min=1,sec=59;
		try{
			do{
					Thread.sleep(1000);
					if(min==0&&sec==0)
					{
						min=-1;
						sec=-1;
						btnResendOtp.setEnabled(true);
					}
					else{
						if(sec==0){
							min--;
							sec=59;
						}
						else
							sec--;
					}
					lblWaitForSec.setText("Wait For a min::["+min+":"+sec+"]");
			}while(min!=-1&&sec!=-1);
			lblWaitForSec.setText("");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
