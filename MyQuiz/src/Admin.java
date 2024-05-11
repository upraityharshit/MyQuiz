import java.awt.EventQueue;

import javax.swing.JFrame;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends JFrame implements ActionListener,FocusListener{

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton signup,find;
	private JLabel msg;
	private JComboBox jcb;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin("insert");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String operation;
	public Admin(String ope) {
		operation=ope;
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(-8, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Entry");
		lblAdminLogin.setForeground(Color.ORANGE);
		lblAdminLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 39));
		lblAdminLogin.setBounds(222, 11, 269, 47);
		frame.getContentPane().add(lblAdminLogin);
		
		JLabel lblAdminId = new JLabel("Admin ID :");
		lblAdminId.setForeground(new Color(255, 218, 185));
		lblAdminId.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblAdminId.setBounds(125, 101, 132, 28);
		frame.getContentPane().add(lblAdminId);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(255, 218, 185));
		lblPassword.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblPassword.setBounds(125, 162, 132, 33);
		frame.getContentPane().add(lblPassword);
		
		if(operation=="insert"){
			textField = new JTextField();
			textField.setBounds(319, 107, 228, 28);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			textField.addFocusListener(this);
			msg=new JLabel("");
			msg.setBounds(550,107,300,28);
			msg.setForeground(Color.cyan);
			frame.getContentPane().add(msg);
		}
		else
		{
			jcb=new JComboBox();
			jcb.setBounds(319,107,228,28);
			frame.getContentPane().add(jcb);
			find= new JButton("Find");
			find.setBounds(550,107,100,28);
			frame.getContentPane().add(find);
			find.addActionListener(this);
			fillid();
		}
		
		if(operation=="insert")
			signup = new JButton("SignUp");
		else if(operation=="delete")
			signup = new JButton("delete");
		else if(operation=="update")
			signup = new JButton("update");
		signup.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		signup.setBounds(173, 375, 139, 47);
		frame.getContentPane().add(signup);
		signup.addActionListener(this);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.ITALIC, 25));
		btnCancel.setBounds(381, 373, 132, 47);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(new Color(255, 218, 185));
		lblName.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblName.setBounds(125, 230, 132, 28);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(321, 162, 228, 32);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(321, 227, 226, 31);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(319, 286, 228, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblRemarks = new JLabel("Remarks :");
		lblRemarks.setForeground(new Color(255, 218, 185));
		lblRemarks.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblRemarks.setBounds(125, 286, 132, 28);
		frame.getContentPane().add(lblRemarks);
		
		JButton btnLogin = new JButton("LogIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage window = new MainPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLogin.setBounds(666, 11, 108, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminPage window = new AdminPage();
				window.frame.setVisible(true);
				frame.hide();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBack.setBounds(10, 10, 108, 23);
		frame.getContentPane().add(btnBack);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel.setBounds(0, 0, 1380, 759);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
	
	Connection con;
	ResultSet rs;
	PreparedStatement ps;
	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==signup)
		{
			if(operation=="insert"){
				String Id=textField.getText();
				String pass=textField_1.getText();
				String name=textField_2.getText();
				String remarks=textField_3.getText();
				if(Id.length()==0||pass.length()==0||name.length()==0||textField_3.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this.frame, "Please Enter all Field");
				}
				else{
					try
					{
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
						String str="insert into admin values(?,?,?,?)";
						ps=con.prepareStatement(str);
						ps.setString(1, Id);
						ps.setString(2, pass);
						ps.setString(3, name);
						ps.setString(4, remarks);
						int i=ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "Data Saved...Successfully");
						con.close();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(this.frame,e.toString());
					}
				}
			}
			else if(operation=="delete"){
				try
				{
					String Id=jcb.getSelectedItem().toString();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
					String str="delete from admin where adminid=?";
					ps=con.prepareStatement(str);
					ps.setString(1, Id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "Data Deleted...Successfully");
					con.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,e.toString());
				}
			}
			else if(operation=="update"){
				try
				{
					String Id=jcb.getSelectedItem().toString();
					String pass=textField_1.getText();
					String name=textField_2.getText();
					String remarks=textField_3.getText();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
					String str="update admin set passwrd=?,name=?,remarks=? where adminid=?";
					ps=con.prepareStatement(str);
					ps.setString(1, pass);
					ps.setString(2, name);
					ps.setString(3, remarks);
					ps.setString(4, Id);
					int i=ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "Data Updated...Successfully");
					con.close();
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,e.toString());
				}
			}
		}
		else if(ar.getSource()==find)
		{
			if(jcb.getSelectedItem().toString().length()!=0){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
				String sql="select passwrd,name,remarks from admin where adminid=?";
				ps=con.prepareStatement(sql);
				ps.setString(1,jcb.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next())
				{
					textField_1.setText(rs.getString(1));
					textField_2.setText(rs.getString(2));
					textField_3.setText(rs.getString(3));
				}
				signup.setEnabled(true);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"Error...."+toString());
			}
			}
			else
				JOptionPane.showMessageDialog(this,"Empty Id..");
		}

	}
	
	public boolean getid(String Id)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
			String str="select * from admin where adminId=?";
			ps=con.prepareStatement(str);
			ps.setString(1, Id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e){
			return true;
		}
	}
	
	public void focusLost(FocusEvent arg0) {
			boolean bet=getid(textField.getText());
			if(bet==true||textField.getText().length()==0)
			{
				msg.setText("This ID already exixts...");
				signup.setEnabled(false);
			}
			else{
				msg.setText("");
				signup.setEnabled(true);
			}
		
		}
	public void focusGained(FocusEvent arg0) {}
	public void fillid()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","root");
			String str="select adminid from admin";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next())
			{
				jcb.addItem(rs.getString(1).toString());
			}
		}
		catch(Exception e) {}
	}
}
