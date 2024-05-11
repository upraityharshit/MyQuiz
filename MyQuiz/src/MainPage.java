import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import conn.DatabaseConnect;

import java.sql.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Button;

public class MainPage implements ActionListener,Runnable{
	
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton Login;
	ButtonGroup bg;
	JRadioButton user,admin;
	String name="user";
	private JLabel lblNewLabel,lblNewLabel_1;
	private JSeparator separator_1;
	private JSeparator separator_2;
	JLabel lblNewLabel_6;
	Thread t;
	int x=270;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainPage() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setBounds(-4, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		bg=new ButtonGroup();
		user = new JRadioButton("STUDENT",true);
		user.setFont(new Font("Tahoma", Font.BOLD, 15));
		user.setBackground(new Color(65, 105, 225));
		user.setBounds(1020, 381, 99, 23);
		user.addActionListener(this);
		
		admin = new JRadioButton("ADMIN");
		admin.setBackground(new Color(65, 105, 225));
		admin.setFont(new Font("Tahoma", Font.BOLD, 15));
		admin.setBounds(1127, 381, 109, 23);
		admin.addActionListener(this);
		
		JLabel lblSelectLoginMode = new JLabel("Select Login Mode :");
		lblSelectLoginMode.setForeground(Color.CYAN);
		lblSelectLoginMode.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblSelectLoginMode.setBounds(774, 376, 184, 32);
		frame.getContentPane().add(lblSelectLoginMode);
		
		bg.add(user);
		bg.add(admin);
		frame.getContentPane().add(user);
		frame.getContentPane().add(admin);
		
		lblNewLabel = new JLabel("Enter Your ID :");
		lblNewLabel.setForeground(new Color(0, 206, 209));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(765, 428, 158, 35);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(1006, 431, 271, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("LOGIN PANEL");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(865, 312, 285, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Your Password :");
		lblNewLabel_2.setForeground(new Color(0, 191, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_2.setBounds(765, 488, 204, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		Login = new JButton("LogIn");
		Login.setBackground(new Color(240, 230, 140));
		Login.setForeground(new Color(0, 0, 0));
		Login.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		Login.setBounds(820, 552, 204, 35);
		frame.getContentPane().add(Login);
		Login.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(new Color(240, 230, 140));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		btnNewButton_1.setBounds(1068, 552, 178, 35);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if(a.getSource()==btnNewButton_1){
					System.exit(1);
				}			
			}
		});
		
		passwordField = new JPasswordField();
		passwordField.setBounds(1006, 483, 271, 35);
		frame.getContentPane().add(passwordField);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(255, 0, 0));
		separator_1.setBounds(714, 362, 606, 12);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.RED);
		separator.setBounds(714, 679, 606, 12);
		frame.getContentPane().add(separator);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.RED);
		separator_2.setBounds(714, 295, 606, 6);
		frame.getContentPane().add(separator_2);
		
		JButton btnForgetPassword = new JButton("Forget password");
		btnForgetPassword.setBackground(Color.DARK_GRAY);
		btnForgetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Forgetpassword window = new Forgetpassword();
				window.frame.setVisible(true);
			}
		});
		btnForgetPassword.setForeground(Color.YELLOW);
		btnForgetPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnForgetPassword.setBounds(935, 606, 208, 32);
		frame.getContentPane().add(btnForgetPassword);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(169, 169, 169));
		menuBar.setForeground(new Color(255, 0, 0));
		menuBar.setBounds(0, 0, 1391, 32);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage window = new MainPage();
				window.frame.setVisible(true);
			}
		});
		mnFile.add(mntmNew);
		
		JSeparator separator_3 = new JSeparator();
		mnFile.add(separator_3);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.RED);
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(713, 295, 10, 385);
		frame.getContentPane().add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(Color.RED);
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(1318, 296, 2, 385);
		frame.getContentPane().add(separator_5);
		

		JLabel lblNewStudentRegistration = new JLabel("New Student Registration");
		lblNewStudentRegistration.setForeground(Color.LIGHT_GRAY);
		lblNewStudentRegistration.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 60));
		lblNewStudentRegistration.setBounds(45, 357, 642, 70);
		frame.getContentPane().add(lblNewStudentRegistration);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(45, 438, 639, 5);
		frame.getContentPane().add(separator_6);
		
		JButton btnNewButton = new JButton("Registration");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Student window = new Student("insert","user");
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setForeground(new Color(255, 255, 240));
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		btnNewButton.setBounds(75, 468, 555, 60);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBackground(Color.YELLOW);
		lblNewLabel_3.setIcon(new ImageIcon(MainPage.class.getResource("/images/motivational-wallpaper-8.jpg")));
		lblNewLabel_3.setBounds(714, 295, 606, 385);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("LOGO");
		lblNewLabel_5.setIcon(new ImageIcon(MainPage.class.getResource("/images/mainlogo (1).png")));
		lblNewLabel_5.setBounds(25, 34, 212, 256);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Online Ex@min@tion $ystem");
		lblNewLabel_6.setForeground(Color.PINK);
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.ITALIC, 57));
		lblNewLabel_6.setBounds(x, 82, 688, 91);
		frame.getContentPane().add(lblNewLabel_6);
		
		t=new Thread(this);
		t.start();
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setForeground(Color.RED);
		separator_7.setBounds(278, 185, 1092, 12);
		frame.getContentPane().add(separator_7);
		
		JLabel lblNewLabel_4 = new JLabel("LogIn");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setIcon(new ImageIcon(MainPage.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel_4.setBounds(0, 4, 1390, 759);
		frame.getContentPane().add(lblNewLabel_4);
		
			
	}

	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==user)
		{
			lblNewLabel.setText("User ID :");
			name="user";
		}
		else if(ar.getSource()==admin)
		{
			lblNewLabel.setText("Admin ID :");
			name="admin";
		}
		else if(ar.getSource()==Login)
		{
			ResultSet rs;
			Connection con;
			PreparedStatement ps;
			if(name=="admin")
			{
				String Id=textField.getText();
				String pass=passwordField.getText();
				if(Id.length()==0||pass.length()==0)
				{
					JOptionPane.showMessageDialog(this.frame, "Please Enter all Field");
				}
				else{
					try
					{
						DatabaseConnect obj=new DatabaseConnect();
						con=obj.Connect();
						String str="select * from admin where adminId=? and passwrd=?";
						ps=con.prepareStatement(str);
						ps.setString(1, Id);
						ps.setString(2, pass);
						rs=ps.executeQuery();
						if(rs.next())
						{
							AdminPage window = new AdminPage();
							window.frame.setVisible(true);
							frame.hide();
						}
						else
						{
							JOptionPane.showMessageDialog(this.frame, "Something went Wrong....try again?");
						}
						con.close();
					}
					catch(SQLException e){
						JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
						e.printStackTrace();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
						e.printStackTrace();
					}
				}
			}
			else if(name=="user")
			{
				String Id=textField.getText();
				String pass=passwordField.getText();
				if(Id.length()==0||pass.length()==0)
				{
					JOptionPane.showMessageDialog(this.frame, "Please Enter all Field");
				}
				else{
					try
					{
						DatabaseConnect obj=new DatabaseConnect();
						con=obj.Connect();
						String str="select * from student where studentid=? and passwrd=?";
						ps=con.prepareStatement(str);
						ps.setString(1, Id);
						ps.setString(2, pass);
						rs=ps.executeQuery();
						if(rs.next())
						{
							UserPage window = new UserPage(Id);
							window.frame.setVisible(true);
							frame.hide();
						}
						else
						{
							JOptionPane.showMessageDialog(this.frame, "Something went Wrong....try again?");
						}
						con.close();
					}
					catch(SQLException e){
						JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
						e.printStackTrace();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
						e.printStackTrace();
					}
				}
			}
			else{
				JOptionPane.showConfirmDialog(this.frame, "Somthing Went Wrong...Check Id or Password");
			}
		}
}
	
	public void run() {
		try{
			while(true){
				while(x<682){
					Thread.sleep(10);
					x+=2;
				    lblNewLabel_6.setBounds(x, 82, 688, 91);
				}
				while(x>270){
					Thread.sleep(10);
					x-=2;
				    lblNewLabel_6.setBounds(x, 82, 688, 91);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
