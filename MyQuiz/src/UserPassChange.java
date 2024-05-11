import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import conn.DatabaseConnect;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class UserPassChange {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	Connection con;
	ResultSet rs,rs1;
	PreparedStatement ps,ps1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPassChange window = new UserPassChange("harshit123");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String idd;
	public UserPassChange(String id) {
		idd=id;
		DatabaseConnect obj=new DatabaseConnect();
		con=obj.Connect();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(200, 100, 600, 400);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setForeground(Color.ORANGE);
		lblChangePassword.setBackground(Color.WHITE);
		lblChangePassword.setFont(new Font("Tahoma", Font.ITALIC, 30));
		lblChangePassword.setBounds(162, 24, 266, 37);
		frame.getContentPane().add(lblChangePassword);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.RED);
		separator.setBounds(123, 72, 348, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblOldPassword = new JLabel("Old Password :");
		lblOldPassword.setForeground(Color.WHITE);
		lblOldPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblOldPassword.setBounds(88, 103, 146, 23);
		frame.getContentPane().add(lblOldPassword);
		
		textField = new JTextField();
		textField.setBounds(295, 106, 198, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblNewPassword.setBounds(88, 148, 146, 23);
		frame.getContentPane().add(lblNewPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(295, 150, 198, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblConfirmPassword.setBounds(88, 194, 177, 23);
		frame.getContentPane().add(lblConfirmPassword);
		
		textField_2 = new JTextField();
		textField_2.setBounds(296, 196, 197, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			private Component frame;

			public void actionPerformed(ActionEvent arg0) {
				try{
					if(textField.getText().length()==0||textField_1.getText().length()==0||textField_2.getText().length()==0){
						JOptionPane.showMessageDialog(frame, "Please Fill All Field's");
					}
					else{
						String old=textField.getText();
						String newpass=textField_1.getText();
						String confirm=textField_2.getText();
						
						String arg="select * from student where passwrd=?";
						ps=con.prepareStatement(arg);
						ps.setString(1, old);
						rs=ps.executeQuery();
						if(rs.next()){
							if(newpass.equals(confirm)){
								String stl="update student set passwrd=? where studentid=?";
								ps=con.prepareStatement(stl);
								ps.setString(1, newpass);
								ps.setString(2, idd);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(frame, "Password Changed Successfully...");
								con.close();
								frame.hide();
							}
							else
								JOptionPane.showMessageDialog(frame, "Confirm Password is not Matched");
						}
						else{
							JOptionPane.showMessageDialog(frame, "Old Password is not Matched");
						}
					}
						
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
				}
			}
		});
		btnChange.setBackground(Color.DARK_GRAY);
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChange.setForeground(Color.WHITE);
		btnChange.setBounds(219, 288, 137, 37);
		frame.getContentPane().add(btnChange);
	}
}
