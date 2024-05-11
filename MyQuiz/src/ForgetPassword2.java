import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.Color;

import javax.swing.ImageIcon;

import conn.DatabaseConnect;
import conn.OtpMail;
import conn.PassReset;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ForgetPassword2 extends Thread{ 

	public JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassword2 window = new ForgetPassword2(12345,"abc@gmail.com","test");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Thread t;
	JLabel label;
	JButton btnResendOtp;
	private JLabel lblNewLabel;
	private JSeparator separator_1;
	private JLabel lblNewPassword;
	private JTextField textField_1;
	private JLabel lblConfirmPassword;
	private JTextField textField_2;
	private JButton btnConfirm;
	private JLabel lblNewLabel_1;
	int OTP;
	String Email,Id;
	Connection con;
	PreparedStatement st,st1;
	
	public ForgetPassword2(int otp,String email,String id) {
		OTP=otp;
		Email=email;
		Id=id;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(300, 100, 741, 449);
		frame.getContentPane().setLayout(null);
		
		t=new Thread(this);
		t.start();
		
		JLabel lblEnterOtp = new JLabel("Enter OTP :");
		lblEnterOtp.setForeground(Color.WHITE);
		lblEnterOtp.setFont(new Font("Times New Roman", Font.ITALIC, 22));
		lblEnterOtp.setBounds(125, 57, 141, 36);
		frame.getContentPane().add(lblEnterOtp);
		
		textField = new JTextField();
		textField.setBounds(324, 57, 288, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnResendOtp = new JButton("Resend OTP");
		btnResendOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new OtpMail(Email,OTP,"ResetPassword");
				
				JOptionPane.showMessageDialog(frame, "OTP Resend successfully");
			}
		});
		btnResendOtp.setBounds(325, 116, 113, 23);
		frame.getContentPane().add(btnResendOtp);
		btnResendOtp.setEnabled(false);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(10, 161, 705, 9);
		frame.getContentPane().add(separator);
		
		label = new JLabel("");
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(448, 116, 217, 23);
		frame.getContentPane().add(label);
		
		lblNewLabel = new JLabel("Reset Password");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(258, 161, 193, 36);
		frame.getContentPane().add(lblNewLabel);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(233, 198, 209, 9);
		frame.getContentPane().add(separator_1);
		
		lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setForeground(new Color(255, 255, 255));
		lblNewPassword.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewPassword.setBounds(107, 229, 166, 29);
		frame.getContentPane().add(lblNewPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(356, 231, 233, 29);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setForeground(new Color(255, 255, 255));
		lblConfirmPassword.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblConfirmPassword.setBounds(107, 284, 202, 29);
		frame.getContentPane().add(lblConfirmPassword);
		
		textField_2 = new JTextField();
		textField_2.setBounds(356, 286, 233, 29);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if( textField.getText().length()!=0){
				if(Integer.parseInt(textField.getText())==OTP)
				{
					if(textField_1.getText().equals(textField_2.getText()))
					{
						DatabaseConnect obj=new DatabaseConnect();
						con=obj.Connect();
						ResultSet rs;
						
						String stp="select email from student where studentid=?";
						String str="update student set passwrd=? where studentid=?";
						try {
							st1=con.prepareStatement(stp);
							st1.setString(1, Id);
							rs=st1.executeQuery();
						if(rs.next())	
						if(rs.getString(1).toString().equals(Email))
						{
							st=con.prepareStatement(str);
							st.setString(1,textField_1.getText().toString());
							st.setString(2,Id);
							st.executeUpdate();
							JOptionPane.showMessageDialog(frame, "Password Changed Successfully...!");
							con.close();
							frame.hide();
							
							new PassReset(Email,Id,textField_1.getText().toString());
						}
						else
							JOptionPane.showMessageDialog(frame, "Email is Not matched to the Records...!");
							
						} 
						catch (SQLException e) {
							e.printStackTrace();
						}
					}
					else{
						JOptionPane.showMessageDialog(frame, "Password is Not Matched");
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "Wrong OTP Entered...!!!");
				}
				}
				else
					JOptionPane.showMessageDialog(frame, "OTP Field is Empty");
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirm.setBounds(245, 346, 166, 36);
		frame.getContentPane().add(btnConfirm);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(ForgetPassword2.class.getResource("/images/wp2519585.jpg")));
		lblNewLabel_1.setBounds(0, 0, 735, 420);
		frame.getContentPane().add(lblNewLabel_1);
		
	}

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
					label.setText("Wait For a min::["+min+":"+sec+"]");
			}while(min!=-1&&sec!=-1);
			label.setText("");
	}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
