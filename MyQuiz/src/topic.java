import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JSeparator;

import java.sql.*;

public class topic extends JFrame implements ActionListener,FocusListener {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnAdd,find;
	private JComboBox jcb; 


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					topic window = new topic("delete");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	String operation;
	public topic(String ope) {
		operation=ope;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(-8, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddTopic = new JLabel("");
		if(operation=="insert")
			lblAddTopic.setText("Add Topic");
		else if(operation=="delete")
			lblAddTopic.setText("Delete Topic");
		else if(operation=="update")
			lblAddTopic.setText("Update Topic");
		lblAddTopic.setForeground(Color.YELLOW);
		lblAddTopic.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblAddTopic.setBounds(247, 39, 300, 35);
		frame.getContentPane().add(lblAddTopic);
		
		JLabel lblTopicId = new JLabel("Topic ID :");
		lblTopicId.setForeground(Color.MAGENTA);
		lblTopicId.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblTopicId.setBounds(137, 131, 111, 25);
		frame.getContentPane().add(lblTopicId);
		
		if(operation=="insert"){
			textField = new JTextField();
			textField.setBounds(325, 131, 205, 26);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(325, 131, 205, 26);
			frame.getContentPane().add(jcb);
			find= new JButton("Find");
			find.setBounds(550,131,100,25);
			frame.getContentPane().add(find);
			find.addActionListener(this);
			fillid();
		}
		
		JLabel lblTopicName = new JLabel("Topic Name :");
		lblTopicName.setForeground(Color.MAGENTA);
		lblTopicName.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblTopicName.setBounds(137, 197, 125, 25);
		frame.getContentPane().add(lblTopicName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(325, 201, 205, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblRemarks = new JLabel("Remarks :");
		lblRemarks.setForeground(Color.MAGENTA);
		lblRemarks.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblRemarks.setBounds(137, 271, 111, 25);
		frame.getContentPane().add(lblRemarks);
		
		textField_2 = new JTextField();
		textField_2.setBounds(325, 271, 205, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		if(operation=="insert")
			btnAdd = new JButton("Add");
		else if(operation=="delete")
			btnAdd = new JButton("delete");
		else if(operation=="update")
			btnAdd = new JButton("update");
		btnAdd.setBackground(new Color(238, 232, 170));
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Tahoma", Font.ITALIC, 30));
		btnAdd.setBounds(150, 347, 150, 35);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(this);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(new Color(238, 232, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 27));
		btnNewButton.setBounds(388, 347, 142, 35);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if(a.getSource()==btnNewButton){
					System.exit(1);
				}			
			}
		});
		
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
		lblNewLabel.setIcon(new ImageIcon(topic.class.getResource("/images/wallpaper2you_94367.jpg")));
		lblNewLabel.setBounds(0, 0, 1390, 759);
		frame.getContentPane().add(lblNewLabel);
		
	
	}

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==btnAdd)
		{
			if(operation=="insert"){	
				if(textField.getText().length()==0||textField_1.getText().length()==0||textField_2.getText().length()==0)
				{
					JOptionPane.showMessageDialog(this.frame, "Please Enter all Field");
				}
				else{
					try
					{
						String Id=textField.getText();
						String topic=textField_1.getText();
						String remarks=textField_2.getText();
						
						Class.forName("com.mysql.jdbc.Driver");
						con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12276081","sql12276081","K3BDtduyWn");
						
						String str="insert into topic values(?,?,?)";
						ps=con.prepareStatement(str);
						ps.setString(1, Id);
						ps.setString(2, topic);
						ps.setString(3, remarks);
						int i=ps.executeUpdate();
						JOptionPane.showMessageDialog(this, "Data Saved...Successfully");
						con.close();
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					}
					catch(SQLException e){
						JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
					}
				}
			}
			else if(operation=="delete"){
				try
				{
					String Id=jcb.getSelectedItem().toString();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12276081","sql12276081","K3BDtduyWn");
					String str="delete from topic where topicid=?";
					ps=con.prepareStatement(str);
					ps.setString(1, Id);
					ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "Data Deleted...Successfully");
					con.close();
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
				}
			}
			else if(operation=="update"){
				try
				{
					String Id=jcb.getSelectedItem().toString();
					String topic=textField_1.getText();
					String remarks=textField_2.getText();
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12276081","sql12276081","K3BDtduyWn");
					String str="update topic set topic=?,remarks=? where topicid=?";
					ps=con.prepareStatement(str);
					ps.setString(3, Id);
					ps.setString(1, topic);
					ps.setString(2, remarks);
					int i=ps.executeUpdate();
					JOptionPane.showMessageDialog(this, "Data Updated...Successfully");
					con.close();
					textField_1.setText("");
					textField_2.setText("");
				}
				catch(SQLException e){
					JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
				}
			}
		}
		else if(ar.getSource()==find)
		{
			if(jcb.getSelectedItem().toString().length()!=0){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12276081","sql12276081","K3BDtduyWn");
				String sql="select topic,remarks from topic where topicid=?";
				ps=con.prepareStatement(sql);
				ps.setString(1,jcb.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next())
				{
					textField_1.setText(rs.getString(1));
					textField_2.setText(rs.getString(2));					
				}
				btnAdd.setEnabled(true);
			}
			catch(SQLException e){
				JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
			}
			}
			else
				JOptionPane.showMessageDialog(this,"Empty Id..");
		}
	}
	
	public void fillid()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12276081","sql12276081","K3BDtduyWn");
			String str="select topicid from topic";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next())
			{
				jcb.addItem(rs.getString(1).toString());
			}
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(this.frame,"Check Internet Connection");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this.frame,"There are Some issue....try again");
		}
	}

	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
