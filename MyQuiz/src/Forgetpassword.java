import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import conn.OTPGenerate;
import conn.OtpMail;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Forgetpassword {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forgetpassword window = new Forgetpassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Forgetpassword() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(300, 100, 741, 400);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterYourRecovery = new JLabel("Enter Some Details to Reset Password");
		lblEnterYourRecovery.setBackground(Color.DARK_GRAY);
		lblEnterYourRecovery.setForeground(new Color(224, 255, 255));
		lblEnterYourRecovery.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		lblEnterYourRecovery.setBounds(71, 38, 580, 50);
		frame.getContentPane().add(lblEnterYourRecovery);
		
		textField = new JTextField();
		textField.setBounds(280, 145, 371, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUserId = new JLabel("User ID :");
		lblUserId.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblUserId.setForeground(Color.CYAN);
		lblUserId.setBounds(97, 145, 149, 30);
		frame.getContentPane().add(lblUserId);
		
		JLabel lblEmailId = new JLabel("Email ID :");
		lblEmailId.setForeground(Color.CYAN);
		lblEmailId.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblEmailId.setBounds(97, 209, 154, 30);
		frame.getContentPane().add(lblEmailId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(280, 209, 371, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSendOtp = new JButton("Send OTP");
		btnSendOtp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField.getText().length()==0||textField_1.getText().length()==0){
					JOptionPane.showMessageDialog(frame, "Please Fill Both Fields");
				}
				else{
					OTPGenerate opt=new OTPGenerate();
					int otp=opt.OTP();
					String email=textField_1.getText().toString();
					String id=textField.getText().toString();
					
					new OtpMail(email,otp,"ResetPassword");
					
					ForgetPassword2 window = new ForgetPassword2(otp,email,id);
					window.frame.setVisible(true);
					frame.hide();
				}
			}
		});
		btnSendOtp.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSendOtp.setBounds(280, 294, 184, 30);
		frame.getContentPane().add(btnSendOtp);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 0, 0));
		separator.setBounds(49, 87, 618, 8);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Forgetpassword.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel.setBounds(0, 0, 735, 371);
		frame.getContentPane().add(lblNewLabel);
		
	
	
	}
}
